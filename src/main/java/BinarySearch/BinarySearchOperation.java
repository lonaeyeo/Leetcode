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

    /**
     * 搜索二维矩阵
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int curr = matrix[mid / matrix[0].length][mid % matrix[0].length];
            if (curr < target)
                left = mid + 1;
            else if (curr > target)
                right = mid - 1;
            else
                return true;
        }
        return false;
    }

    /**
     * 搜索二维矩阵 II
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;
        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            int curr = matrix[row][col];

            if (curr > target)
                // current row are all larger than target
                --row;
            else if (curr < target)
                // column elements above curr are all smaller than target
                ++col;
            else
                return true;
        }
        return false;
    }

    /**
     * 寻找峰值
     * 极大值
     * nums[-1] == nums[length] == -∞
     */
    public int findPeakElement(int[] nums) {
        return locatePeak(nums, 0, nums.length - 1);
    }

    private int locatePeak(int[] nums, int left, int right) {
        if (left == right)
            return left;
        int mid = left + (right - left) / 2;
        // 如果中间值大于右值，那么左边必有极大值，只能改right；
        // 同理，如果中间值小于右值，那么右边必有极大值，只能改left；
        // 当空间大小缩小到1的时候，表示此值就是极大值
        if (nums[mid] > nums[mid + 1])
            return locatePeak(nums, left, mid);
        else
            return locatePeak(nums, mid + 1, right);
    }

    public int findPeakElement_1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如果中间值大于右值，那么左边必有极大值，只能改right；
            // 同理，如果中间值小于右值，那么右边必有极大值，只能改left；
            if (nums[mid] > nums[mid + 1])
                right = mid;
            else
                left = mid + 1;
        }
        // 当 left==right 的时候，表示此值就是极大值
        return left;
    }
}
