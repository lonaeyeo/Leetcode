package ArrayAndSort;

public class Colors {

    /**
     * 三种颜色，0，1，2，排个序
     * <p>
     * 方法1，单指针：O(n), O(1)
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int prt = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                // 交换数据
                nums[i] = nums[prt];
                // [prt]被正确放置0
                nums[prt] = temp;
                prt++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[prt];
                nums[prt] = temp;
                prt++;
            }
        }
    }

    // 方法二，双指针：O(n),O(1)
    public void sortColors2(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }

    }
}
