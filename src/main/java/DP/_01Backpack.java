package DP;

import org.junit.Test;
import org.junit.runner.notification.RunListener;

public class _01Backpack {


    /**
     * 分割等和子集
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len <= 1) return false;

        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }

        if (sum % 2 == 1) return false;
        int target = sum / 2;
        // dp[i][j]：表示在nums[0~i]中是否存在一组和为j的方案
        boolean[][] dp = new boolean[len][target + 1];
        for (int i = 0; i < len; i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j <= target; j++) {
            dp[0][j] = nums[0] == j ? true : false;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= target; j++) {
                boolean temp = j - nums[i] >= 0 ? dp[i - 1][j - nums[i]] : false;
                // 为何保留dp[i-1][j]，因为构建dp数组的需要
                dp[i][j] = dp[i - 1][j] || temp;
                if (dp[i][target]) {
                    return true;
                }
            }
        }

        return false;
    }

    @Test
    public void test() {
        _01Backpack b = new _01Backpack();
        System.out.println(b.canPartition(new int[]{1, 2, 5}));
    }
}
