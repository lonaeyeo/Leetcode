package DP;

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
}
