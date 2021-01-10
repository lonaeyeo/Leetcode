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
}
