package ArrayStringAndSort;

import java.util.Random;

public class FindKthLargestN {

    /**
     * 215. 数组中的第K个最大元素
     * <p>
     * 利用快排思路，partition
     * 用到了Random类
     */
    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        return quickRandomSort(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickRandomSort(int[] nums, int left, int right, int index) {
        // 随机数
        int mid = random.nextInt(right - left + 1) + left;
        // 将nums[mid]换到[right]去
        swap(nums, mid, right);

        // 快排的另一种方式：只从一边开始扫描，所以小于标记数的值依次挪到左边；
        // 直到扫描结束，最后将标记数置于分隔下表ci处
        int ci = left;
        for (int i = left; i < right; i++) {
            // 把小于nums[right]的数都挪到左边
            if (nums[i] < nums[right])
                swap(nums, i, ci++);
        }
        // 别忘了标记的nums[right]换到分界点
        swap(nums, ci, right);

        // 判断标记处小标是否为第k个
        if (ci == index)
            return nums[ci];
        else
            return ci < index ? quickRandomSort(nums, ci + 1, right, index) : quickRandomSort(nums, left, ci - 1, index);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
