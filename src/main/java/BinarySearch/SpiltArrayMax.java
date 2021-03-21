package BinarySearch;

import org.junit.Test;

public class SpiltArrayMax {

    /**
     * leetcode: 410
     * 分割数组的最大值：将数组分割成m部分，得到每个部分的和，计算所有部分和中最大的那个数，并试图得到此数的最小可能
     * 两种思路：dp和二分搜索贪心
     * dp：dp[i][j]：表示前i个数分成j组，
     * dp[i][j] = Math.min(dp[i][j], Math.max(sum[i]-sum[k], dp[k][j-1]))；
     * <p>
     * 二分搜索：区间下界：nums[]中的max，区间上界：从sum开始减；
     * 判断方式：假定每组和比大于mid，计算组数，如果组数小于m，说明“最小可能”还可以更小，反之则更大
     */
    public int splitArray(int[] nums, int m) {
        // 左界右界
        int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            left = Math.max(nums[i], left);
            right += nums[i];
        }

        // <=是因为，left需最后确定一下（宁愿牺牲一些，组数也不会超过m个）
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            // 如果true，说明上界得下来
            if (check(nums, mid, m)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return left;
    }

    // 计算每组和恰好都为<=mid时，并判断组数是否<m
    // 如果组数小于m，未充分利用到m个人，为true，说明上界应该下来点，人数多点，人均分摊少点，
    public boolean check(int[] nums, int mid, int m) {
        // 和超过mid的数量
        int count = 1;
        // 组的和，超过mid，重置并开始计算下组和
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > mid) {
                count++;
                // 重置，不包括nums[i]的sum算为上一组
                sum = nums[i];
            } else {
                // 没超过mid，当前组和继续叠加
                sum += nums[i];
            }
        }
        // 如果相等，也要有下探的可能性，因为求的是最大值的最小值化
        return count <= m;
    }

    @Test
    public void test() {
        SpiltArrayMax s = new SpiltArrayMax();
        System.out.println(s.splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }
}
