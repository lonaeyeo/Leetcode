package BinarySearch;

public class MedianOfTwoArrays {

    /**
     * 寻找两个正序数组的中位数(数组是有序的)
     * 粗暴方法：合并两个数组，时间复杂度O(N+M)，空间复杂度O(M+N)
     * 高级方法：二分查找法，用一条分割线分割两个数组，左边两个子数组长度 >= 右边两个子数组长度
     * a[i-1] <= b[j] && a[i] >= b[j - 1]
     * 判定条件可以是 if a[i-1] <= b[j]：如果为true，left = mid+1；else，right = mid-1
     * 时间复杂度：O(log min(m,n))
     * 空间复杂度：O(1)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);

        int l1 = nums1.length;
        int l2 = nums2.length;
        int left = 0;
        int right = l1;
        int leftMax = 0;
        int rightMin = 0;

        // 需考虑分割线在数组外的可能性，即a[i-1] | a[i]。当然a[i]不存在
        while (left <= right) {
            // 分割线在i和i-1中间，同时a的左侧子数组长度为i
            int i = (right + left) / 2;
            // 那么b的左侧子数组长度就为 （总数-mid）。(l1+l2+1)确保左侧数组长度大于右侧
            int j = (l1 + l2 + 1) / 2 - i;

            int a_i_1 = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int a_i = i == l1 ? Integer.MAX_VALUE : nums1[i];
            int b_j_1 = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int b_j = j == l2 ? Integer.MAX_VALUE : nums2[j];

            if (a_i_1 <= b_j) {
                // 每一个符合条件的分割线数据都会被覆盖记录，使得rightMin-leftMax的值不断增大，直至满足双条件
                leftMax = Math.max(a_i_1, b_j_1);
                rightMin = Math.min(a_i, b_j);
                // left边界往右走，进一步缩小范围
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        // 看总数组长度奇数还是偶数
        return (l1 + l2) % 2 == 0 ? (leftMax + rightMin) / 2.0 : leftMax;
    }
}
