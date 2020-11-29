package ArrayAndSort;

import org.junit.Test;

public class ReversePairs {

    @Test
    public void test() {
        ReversePairs reversePairs = new ReversePairs();
        System.out.println(reversePairs.reversePairs2(new int[]{1, 3, 2, 3, 1}));
    }

    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return 0;
        int[] copy = new int[len];
        for (int i = 0; i < len; i++) {
            copy[i] = nums[i];
        }
        int[] temp = new int[len];
        // temp是临时用空间，节省空间
        return reversePairsHelper(copy, 0, len - 1, temp);
    }

    public int reversePairsHelper(int[] nums, int left, int right, int[] temp) {
        if (left == right)
            return 0;
        int mid = left + (right - left) / 2;
        int leftPairs = reversePairsHelper(nums, left, mid, temp);
        int rightPairs = reversePairsHelper(nums, mid + 1, right, temp);
        // 如果顺序上升，否则不建议用下列判断式
        if (nums[mid] <= nums[mid + 1])
            return leftPairs + rightPairs;
        int mPairs = mergePairs(nums, left, mid, right, temp);
        return leftPairs + rightPairs + mPairs;
    }

    public int mergePairs(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int pairs = 0;
        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            // 判断 i是否用完
            if (i == mid + 1) {
                nums[k] = temp[j];
                ++j;
                // 判断j 是否用完
            } else if (j == right + 1) {
                nums[k] = temp[i];
                ++i;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                ++i;
            } else {
                nums[k] = temp[j];
                ++j;
                pairs += (mid + 1 - i);
            }
        }
        return pairs;

    }

    /*
    -------------两倍差值
     */
    public int reversePairs2(int[] nums) {
        if (nums.length < 1)
            return 0;
        return reversePairsRecursive(nums, 0, nums.length - 1);
    }

    public int reversePairsRecursive(int[] nums, int left, int right) {
        if (left == right)
            return 0;

        int mid = left + (right - left) / 2;
        int leftPairs = reversePairsRecursive(nums, left, mid);
        int rightPairs = reversePairsRecursive(nums, mid + 1, right);
        int ret = leftPairs + rightPairs;

        // 计算当前 数组的 翻转对数，前后两个数组皆是各自有序
        int i = left;
        int j = mid + 1;
        while (i <= mid) {
            while (j <= right && (long) nums[i] > (long) nums[j] * 2) {
                ++j;
            }
            ret += j - 1 - mid;
            ++i;
        }

        // 将两段数组合并并排序
        int[] sorted = new int[right - left + 1];
        int lcur = left;
        int rcur = mid + 1;
        for (int k = 0; k < sorted.length; k++) {
            if (lcur == mid + 1) {
                sorted[k] = nums[rcur];
                ++rcur;
            } else if (rcur == right + 1) {
                sorted[k] = nums[lcur];
                ++lcur;
            } else if (nums[lcur] > nums[rcur]) {
                sorted[k] = nums[rcur];
                ++rcur;
            } else {
                sorted[k] = nums[lcur];
                ++lcur;
            }
        }

        for (int k = left, p = 0; k <= right; ++k, ++p) {
            nums[k] = sorted[p];
        }

        return ret;
    }
}
