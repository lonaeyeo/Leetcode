package DP;

import org.junit.Test;

public class Balloon {

    /**
     * [i, j]：开区间，k为最后一个戳破的气球
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 预留边界情况，左右不存在的气球1，分别对应为[0]、[n+1];
        int[][] dp = new int[n + 2][n + 2];

        for (int i = n - 1; i >= 0; i--) {
            // 注意i在nums中要减1
            int left = i == 0 ? 1 : nums[i - 1];
            for (int j = i + 2; j <= n + 1; j++) {
                int right = j == n + 1 ? 1 : nums[j - 1];
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + left * nums[k - 1] * right);
                }
            }
        }

        return dp[0][n + 1];
    }

    @Test
    public void test() {
        Balloon b = new Balloon();
        System.out.println(b.maxCoins(new int[]{3, 1, 5, 8}));
    }
}
