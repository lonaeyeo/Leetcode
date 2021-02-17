package DP;

public class Rob {

    /**
     * 打家劫舍1
     * 不可连续两个
     */
    public int rob1(int[] nums) {
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


    /**
     * 打家劫舍2
     * 循环数组
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

        if (len == 3)
            return Math.max(nums[2], res);

        if (len > 3) {
            // 第一家必打劫，那么最后一个必不打劫
            dp[2] = dp[0] + nums[2];
            res = Math.max(res, dp[2]);
            for (int i = 3; i < len - 1; i++) {
                dp[i] = Math.max(dp[i - 2], dp[i - 3]) + nums[i];
                res = Math.max(dp[i], res);
            }

            // 第一家必不打劫，最后一个必打劫
            dp[0] = 0;
            dp[2] = nums[2];
            for (int i = 3; i < len; i++) {
                dp[i] = Math.max(dp[i - 2], dp[i - 3]) + nums[i];
                res = Math.max(dp[i], res);
            }
        }
        return res;
    }
}
