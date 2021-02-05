package DP;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayOperation {

    /**
     * 最长连续递增序列
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) return 0;

        int len = 1;
        int maxLen = 1;
        int prev = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > prev)
                ++len;
            else
                len = 1;
            prev = nums[i];
            maxLen = Math.max(len, maxLen);
        }
        return maxLen;
    }

    /**
     * 最长续增子序列（不连续）
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (nums.length == 0) return 0;
        // 以[i]结尾的最长递增子序列
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int ans = 1;

        for (int i = 1; i < len; i++) {
            // 暴力
            for (int j = 0; j < i; j++) {
                // 扫描之前每一个dp，发现比自己小的就+1，并和自己比较大
                // 所以一路都会是“当前最大”的状态
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
