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


    /**
     * 接雨水 （类似：最大矩形）
     * 给定一组高度，测算可接雨水大小。height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 首先，测定可接雨水涉及到池子两边高度，所以必定会涉及到left和right的值，
     * 即当前池子的高度 = rightMax - leftMax
     * <p>
     * 基本动态规划方法：牺牲空间记录每个池子的对应rightMax和leftMax
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len == 0) return 0;
        int[] leftMaxs = new int[len];
        int[] rightMaxs = new int[len];
        leftMaxs[0] = height[0];
        rightMaxs[len - 1] = height[len - 1];

        for (int i = 1; i < len; i++) {
            // 这个过程会不断传递其目前左侧的最高值
            leftMaxs[i] = Math.max(leftMaxs[i - 1], height[i]);
        }
        for (int i = len - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMaxs[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 1; i < len - 1; i++) {
            ans += Math.min(leftMaxs[i], rightMaxs[i]) - height[i];
        }
        return ans;
    }

    /**
     * 打家劫舍
     * 不可连续两个
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        else if (len == 1) return nums[0];

        // dp[i]表示选择并以[i]结尾的最大总和
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = nums[1];
        int res = Math.max(dp[0], dp[1]);

        for (int i = 2; i < len; i++) {
            int temp = i - 3 >= 0 ? dp[i - 3] : 0;
            // 当前[i]加上dp[i-2]或者dp[i-3]，看谁大就加谁
            dp[i] = nums[i] + Math.max(dp[i - 2], temp);
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
