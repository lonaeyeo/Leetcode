package DP;

import org.junit.Test;

import java.util.*;

public class StringOperation {

    /**
     * 最长回文子串
     * dp[][]：表示从i到j是回文串
     * [[1 2 3 4 5]
     * [  1 2 3 4]
     * [    1 2 3]
     * [      1 2]
     * [        1]]
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        // l是回文串长度，l为0时，回文串长度为1；
        for (int l = 0; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                int j = i + l;
                if (l == 0) {
                    // 回文串长度为1，皆为true
                    dp[i][j] = true;
                } else if (l == 1) {
                    // 回文串长度为2，看两个元素是否相等
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    // 回文串长度>=3，看边缘两个元素是否相等，并且中间回文串是否为true
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }

                if (l + 1 > ans.length() && dp[i][j])
                    ans = s.substring(i, j + 1);
            }
        }
        return ans;
    }


    /**
     * 最长公共子序列
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case：一个字符串为空，那么最长公共子序列长度为0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // dp[i-1][j-1]必定是<=dp[i][j-1]或者dp[i-1][j]
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


    /**
     * 最长有效括号
     */
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len <= 1) return 0;
        // 以s[i]结尾
        int[] dp = new int[len];
        int max = 0;

        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    int temp = i - 2 >= 0 ? dp[i - 2] : 0;
                    dp[i] = 2 + temp;
                } else if (dp[i - 1] > 0) {
                    int temp = i - 1 - dp[i - 1];
                    if (temp >= 0 && s.charAt(temp) == '(')
                        // s[temp]前面相连有效子串 也要加上去
                        dp[i] = dp[i - 1] + 2 + (temp > 0 ? dp[temp - 1] : 0);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }


    /**
     * 最大正方形
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 以[i][j]为右小角的最大正方形
        int[][] dp = new int[m][n];

        //
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1')
                dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1')
                dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1')
                    dp[i][j] = getMin(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1;
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dp[i][j]);
            }
        }

        return res * res;
    }

    private int getMin(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }


    /**
     * 单词划分，完整划分
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 如果出现多个字符串匹配的情况，应该考虑使用HashSet
        Set<String> wordDictSet = new HashSet<>(wordDict);
        // 以[i]结尾是否可划分，[0]预留
        boolean[] dp = new boolean[s.length() + 1];
        // dp中0表示无j划分的情况
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            // 将[j]作为分割点，判断[j+1, i]是否是个词
            for (int j = 0; j < i; j++) {
                // 注意在set中下标应该-1
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    /**
     * 分割回文串
     */
    boolean[][] dp;
    List<List<String>> lists = new ArrayList<>();
    List<String> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        int len = s.length();
        dp = new boolean[len][len];

        // dp记录[i, j]是否为回文串
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 如果i+1>j-1，temp应该置为true
                    boolean temp = i + 1 <= j - 1 ? dp[i + 1][j - 1] : true;
                    dp[i][j] = temp;
                }
            }
        }

        dfs(s, 0);
        return lists;
    }

    public void dfs(String s, int i) {
        if (i == s.length()) {
            // 此种实例化方法，会把ans的内容复制一遍
            lists.add(new ArrayList<String>(ans));
            return;
        }

        for (int j = i; j < s.length(); j++) {
            if (dp[i][j]) {
                ans.add(s.substring(i, j + 1));
                // 如果[i, j]是回文串，那就看[j+1, len]可以划分成什么样的回文串
                dfs(s, j + 1);
                // 向上回溯时，应删除 旧的解
                ans.remove(ans.size() - 1);
            }
        }
    }

    @Test
    public  void test(){
        StringOperation s = new StringOperation();
        System.out.println(s.partition("aab"));
    }
}
