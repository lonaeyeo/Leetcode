package ArrayAndSort;

import org.junit.Test;

import java.util.Arrays;

public class ToughClassP1 {

    @Test
    public void test() {
        ToughClassP1 toughClassP1 = new ToughClassP1();
        int[] ans = toughClassP1.maxNumber(new int[]{8, 6, 9}, new int[]{1, 7, 5}, 3);
        for (int a : ans) {
            System.out.println(a);
        }
//        System.out.println(ToughClassP1.reversePairs(new int[]{1, 3, 2, 3, 1}));
//        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE >> 1));
//        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
    }

    public static int maximumGap(int[] nums) {
        if (nums.length <= 1)
            return 0;

        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        // 算出每段长度d，最大间距必定大于d
        int d = Math.max(1, (max - min) / (nums.length - 1));
        // 根据d 算出buckets的size
        int bucketsSize = (max - min) / d + 1;
        int[][] buckets = new int[bucketsSize][2];

        for (int[] bucket : buckets) {
            Arrays.fill(bucket, -1);
        }

        // 装进桶中，宽度为d数量为n的桶
        for (int i = 0; i < nums.length; ++i) {
            int flag = (nums[i] - min) / d;
            if (buckets[flag][0] == -1) {
                buckets[flag][0] = nums[i];
                buckets[flag][1] = nums[i];
            } else if (buckets[flag][0] > nums[i]) {
                buckets[flag][0] = nums[i];
            } else if (buckets[flag][1] < nums[i]) {
                buckets[flag][1] = nums[i];
            }
        }

        int prev = -1;
        int ans = 0;
        for (int i = 0; i < buckets.length; ++i) {
            if (buckets[i][0] != -1) {
                // 记录初次prev位置
                if (prev == -1)
                    prev = i;
                ans = Math.max(ans, buckets[i][0] - buckets[prev][1]);
                prev = i;
            }
        }
        return ans;
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int s1Limit = nums1.length;
        int s2Limit = nums2.length;
        int s1Curr = 0;
        int s2Curr = 0;
        int max1 = s1Curr;
        int max2 = s2Curr;

        int[] ans = new int[k];
        int index = 0;
        int leftK = k;

        while (leftK > 0) {
            max1 = s1Curr;
            max2 = s2Curr;
            int forwardBoundary = nums1.length - s1Curr + nums2.length - s2Curr - leftK;
            s1Limit = s1Curr + forwardBoundary < nums1.length - 1 ? s1Curr + forwardBoundary : nums1.length - 1;
            s2Limit = s2Curr + forwardBoundary < nums2.length - 1 ? s2Curr + forwardBoundary : nums2.length - 1;

            for (int i = s1Curr; i <= s1Limit; ++i) {
                if (nums1[i] > nums1[max1])
                    max1 = i;
            }
            for (int i = s2Curr; i <= s2Limit; ++i) {
                if (nums2[i] > nums2[max2])
                    max2 = i;
            }

            if (s1Curr == nums1.length) {
                ans[index] = nums2[max2];
                s2Curr = max2 + 1;
            } else if (s2Curr == nums2.length) {
                ans[index] = nums1[max1];
                s1Curr = max1 + 1;
            } else {
                if (nums1[max1] >= nums2[max2]) {
                    ans[index] = nums1[max1];
                    s1Curr = max1 + 1;
                } else {
                    ans[index] = nums2[max2];
                    s2Curr = max2 + 1;
                }
            }

            ++index;
            --leftK;
        }
        return ans;
    }

}
