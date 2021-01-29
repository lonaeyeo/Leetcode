package BinarySearch;

public class RotatedArrays {

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
     * 搜索旋转排序数组 II
     * 含重复数据
     */
    public boolean search2(int[] nums, int target) {
        if (nums.length == 0)
            return false;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return true;
            if (nums[left] < nums[mid]) {
                // 有序空间里不包含target，那么剩余可能性完全取决于 右无序空间
                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else if (nums[left] > nums[mid]) {
                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            } else {
                // 如果左值和中间值相同，不妨使left+1
                ++left;
            }
        }
        return false;
    }

    /**
     * 寻找旋转排序数组中的最小值
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int min = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 表示左半为有序数组
            if (nums[left] <= nums[mid]) {
                min = Math.min(nums[left], min);
                // 同时设定下次扫描范围为 右且无序部分
                left = mid + 1;
            } else {
                min = Math.min(nums[mid + 1], min);
                right = mid;
            }
        }
        return min;
    }

    /**
     * 寻找旋转排序数组中的最小值（包含重复数据）
     * c
     * o
     * o o           o
     * c o
     * o
     */
    public int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;

        while (left <= right) {
            mid = left + (right - left) / 2;

            /*
             如果nums[mid]小于nums[right]，那么mid右边的任何数字必定 >= nums[mid]，
             那就可以排除mid右边部分的数据了；
             如果nums[mid]大于nums[right]，那么nums必定是个旋转数组，而旋转数组中nums[0] >= nums[n]，
             所以mid左边的数字必定 >= nums[right]，那么就可以排除mid左边的数据了；
             如果nums[mid] == nums[right]，并不能去除右部分，但是right--，去掉一个
             */
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // 如果恰巧是最小数，那么会通过right--，使得left>right来退出循环
                --right;
            }
        }
        return nums[mid];
    }


}
