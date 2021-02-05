package DP;

public class MinimalEditDistance {

    /**
     * 最短编辑距离
     *
     * base case：其中一个字符串为空，那么最短编辑距离就是另一个字符串的长度
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 预留出m=0，n=0的情况
        int[][] dp = new int[m + 1][n + 1];
        // 当一边为空字符，那么最短编辑距离应该为另一边的长度
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 注意字符串索引要-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 相等就不做任何处理，直接跳过
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int a = dp[i][j - 1] + 1; // 插入
                    int b = dp[i - 1][j] + 1; // 删除
                    int c = dp[i - 1][j - 1] + 1; // 替换
                    dp[i][j] = min(a, b, c);
                }
            }
        }

        return dp[m][n];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
