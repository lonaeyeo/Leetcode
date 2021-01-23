package BinarySearch;

import org.junit.Test;

public class BinarySearchOperation {

    @Test
    public void test() {
        BinarySearchOperation b = new BinarySearchOperation();
        System.out.println(Integer.MIN_VALUE / 1);
        System.out.println(Integer.MIN_VALUE / -1);
        System.out.println(Integer.MIN_VALUE);
//        System.out.println(b.divide(Integer.MIN_VALUE, -1));
    }

    /**
     * 两数相除，不能用乘除法
     * divisor二倍变化来逼近dividend
     */
    public int divide(int dividend, int divisor) {
        if (divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;

        int sign = (dividend ^ divisor) < 0 ? -1 : 1;
        // 将数字转换成负数来处理，更好应对溢出情况 [2^32, 2^32-1]
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;

        int ans = divideHelper(dividend, divisor);
        return sign < 0 ? -ans : ans;
    }

    private int divideHelper(int dividend, int divisor) {
        if (dividend > divisor) return 0;

        int count = 1;
        int tmp = divisor;

        // tmp+tmp<0 处理负数因溢出而变成正数的问题
        while (tmp + tmp < 0 && tmp + tmp >= dividend) {
            count += count;
            tmp += tmp;
        }
        return count + divideHelper(dividend - tmp, divisor);
    }

    /**
     * 在旋转数组中查找target，旋转数组为曾经有序的数组
     * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
     * 思路：数组中有一半必定是有序的，另一半则不是有序的；通过二分的思路，逐渐locate target的位置
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;
        int left = 0;
        int right = nums.length - 1;

        // 每次充分划分的[left,right]中必有一个有序左（右）部分以及无序右（左）部分
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            // 此判断句表示[left, mid]区间是有序的，那么[mid-1,right]是无序的
            // <=：因为当子数组长度为1时，left==mid，需考虑边界情况
            if (nums[left] <= nums[mid]) {
                // 此处限定了target在有序[left,mid]严格区间的条件
                if (nums[mid] > target && target >= nums[left])
                    right = mid - 1;
                else
                    // target在乱序的[mid+1,right]中
                    left = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 寻找有序数组中target的左右边界
     * leftBound: 求左边界
     * rightBound: 求右边界
     * boundary: 求边界（左右皆可）
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        int leftB = boundary(nums, target, true);
        int rightB = boundary(nums, target, false);
        return new int[]{leftB, rightB};
    }

    private int leftBound(int[] nums, int target) {
        if (nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                // 因为求的是左边界，所以在确定target==nums[mid]的时候，区间右界应该向左靠拢
                right = mid - 1;
        }

        // 如果left过界或者“right过界且nums[left]!=target”，表示target根本不存在
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }

    private int rightBound(int[] nums, int target) {
        if (nums.length == 0)
            return 0;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                left = mid + 1;
        }

        // 如果right过界或者“left过界且nums[right]!=target”，表示target根本不存在
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }

    private int boundary(int[] nums, int target, boolean isLeft) {
        if (nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (isLeft)
                // 因为求的是左边界，所以在确定target==nums[mid]的时候，区间右界应该向左靠拢
                right = mid - 1;
            else
                left = mid + 1;
        }

        // 如果left过界或者“right过界且nums[left]!=target”，表示target根本不存在
        if (isLeft && (left >= nums.length || nums[left] != target))
            return -1;
        // 如果right过界或者“left过界且nums[right]!=target”，表示target根本不存在
        if (!isLeft && (right < 0 || nums[right] != target))
            return -1;

        return isLeft ? left : right;
    }

    /**
     * 实现 n次方x
     */
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        return n > 0 ? quickMul(x, n) : 1 / quickMul(x, -n);
    }

    private double quickMul(double x, int n) {
        if (n == 0) return 1.0;
        double y = quickMul(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }
}
