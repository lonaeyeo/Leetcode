import org.junit.Test;

import java.util.Arrays;

public class NextPermutation {

    @Test
    public void test() {
        NextPermutation nextPermutation = new NextPermutation();
        int[] nums = {8, 9, 8};
        nextPermutation.nextPermutation(nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }

    public void nextPermutation(int[] nums) {
        int flag = 0;

        for (int i = nums.length - 1; i > 0; --i) {
            if (nums[i] > nums[i - 1]) {
                int currentValue = nums[i - 1];
                int largerButMin = i;

                // 获取大于currentValue但最小的值
                for (int j = i + 1; j < nums.length; ++j) {
                    if (nums[j] > currentValue) {
                        largerButMin = nums[j] < nums[largerButMin] ? j : largerButMin;
                    }
                }

                // 交换当前i值和大于但最小值
                swap(nums, i - 1, largerButMin);

                // 升序剩余值
                Arrays.sort(nums, i, nums.length);
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
}
