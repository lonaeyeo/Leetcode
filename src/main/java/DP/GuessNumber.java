package DP;

import org.junit.Test;

import java.util.Arrays;

public class GuessNumber {

    /**
     * 猜数字大小2
     * 极小极大化题目，可用dp
     * dp[][]
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                // 初试第一种情况，直接赋值
                dp[i][j] = i + dp[i + 1][j];
                for (int k = i + 1; k < j; k++) {
                    // 如果起始猜点为k的话，需求得最大的情况，才能满足起始k两边的最低所需代价
                    int temp = Math.max(k + dp[i][k - 1], k + dp[k + 1][j]);
                    // 此时temp代表的是k对应的必须代价，和已有dp[i][j]比较，求最小即可
                    dp[i][j] = Math.min(dp[i][j], temp);
                }
                // 只需找到满足最低代价的即可
                dp[i][j] = Math.min(dp[i][j], j + dp[i][j - 1]);
            }
        }

        return dp[1][n];
    }

    @Test
    public void test() {
        GuessNumber g = new GuessNumber();
        System.out.println(g.getMoneyAmount(11));
    }
}
