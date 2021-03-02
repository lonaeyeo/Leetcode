package DP;

public class MaxRectangle {

    // O(M^2*N)
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxArea = 0; //最大面积
        int width = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
                    width = dp[i][j];

                    for (int k = i; k >= 0; k--) {
                        // width 和 maxarea 必须来回比较
                        // 如果有dp[k][j]为0，通过width的传递可使得当前面积为0

                        width = Math.min(width, dp[k][j]);
                        maxArea = Math.max(maxArea, width * (i - k + 1));
                    }
                }
            }
        }
        return maxArea;
    }

    // 单调栈
    public int maximalRectangle2(char[][] matrix) {
        if (matrix.length == 0)
            return 0;

        return 0;
    }
}
