package Array;

import org.junit.Test;

import java.util.HashSet;

public class NextPermutation {

    @Test
    public void test() {
        NextPermutation nextPermutation = new NextPermutation();
//        int[] nums = {2, 3, 1, 3, 3};
//        nextPermutation.nextPermutation(nums);
//        for (int i : nums) {
//            System.out.println(i);
//        }

        int[] nums1 = {0, 1, 0, 3, 12};
        nextPermutation.moveZeroes(nums1);
        for (int num : nums1) {
            System.out.println(num);
        }
    }

    public void nextPermutation(int[] nums) {
        int flag = 0;

        for (int i = nums.length - 1; i > 0; --i) {
            if (nums[i] > nums[i - 1]) {
                int currentValue = nums[i - 1];
                int largerButMin = i;

                // 从后往回扫，获取大于currentValue但最小的值
                for (int j = nums.length - 1; j > i; --j) {
                    if (nums[j] > currentValue) {
                        largerButMin = j;
                        break;
                    }
                }

                // 交换当前i值和大于但最小值
                swap(nums, i - 1, largerButMin);

                // 升序剩余值，revers即可
                int left = i, right = nums.length - 1;
                while (left < right) {
                    swap(nums, left, right);
                    ++left;
                    --right;
                }
                flag = 1;
                break;
            }
        }

        if (flag == 0) {
            for (int i = 0; i < nums.length / 2; ++i) {
                swap(nums, i, nums.length - 1 - i);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int num : nums) {
            if (!hashSet.add(num)) {
                return num;
            }
        }
        return 0;
    }

    public void moveZeroes(int[] nums) {
        int nzero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nzero++;
            } else {
                nums[i - nzero] = nums[i];
            }
        }

        for (int i = 0; i < nzero; ++i) {
            nums[nums.length - 1 - i] = 0;
        }
    }
}
