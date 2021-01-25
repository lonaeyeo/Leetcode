package DP;

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
}
