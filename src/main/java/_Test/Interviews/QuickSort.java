package _Test.Interviews;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort k = new QuickSort();
        int[] A = new int[]{4, 6, 77, 3, 324, 43, 43, 2, 54, 23, 15, 36, 77, 88, 109, 1198, 2378, 7, 1};
        k.quickSort(A, 0, A.length - 1);
        for (int a : A) {
            System.out.print(a + " ");
        }
    }

    public void quickSort(int[] nums, int l, int r) {
        int left = l;
        int right = r;
        if (left >= right)
            return;

        int key = nums[left];
        while (left != right) {
            while (left < right && nums[right] >= key) {
                right--;
            }
            // key所在将会第一个换掉
            nums[left] = nums[right];
            while (left < right && nums[left] <= key) {
                left++;
            }
            nums[right] = nums[left];
            // System.out.println("------------");
        }
        // 记住这里也要
        nums[left] = key;

        quickSort(nums, l, left - 1);
        quickSort(nums, left + 1, r);
    }

}
