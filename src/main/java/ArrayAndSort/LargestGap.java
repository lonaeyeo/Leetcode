package ArrayAndSort;

import org.junit.Test;

import java.util.Arrays;

public class LargestGap {

    @Test
    public void test() {
        System.out.println(LargestGap.maximunGapSimplest(new int[]{1, 10000000}));
    }

    public static int maximunGapSimplest(int[] nums) {
        if (nums.length <= 1)
            return 0;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 1; i < nums.length; ++i) {
            ans = Math.max(ans, nums[i] - nums[i - 1]);
        }
        return ans;
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
}
