package TreeAndNode;


import java.util.LinkedList;
import java.util.List;

public class BinarySearchTreeRecover {

    // 题目：有个两个点错位，恢复即可
    // 思路：中序遍历中，不按顺序的即是有问题的
    public void recoverTree(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        inorder(root, list);
        int[] pair = findTwoMistakes(list);
        recover(root, 2, pair);
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public int[] findTwoMistakes(List<Integer> list) {
        int x1 = -1;
        int x2 = -1;
        // 7和5，x1:7 ； 6和4，x2:4。
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) < list.get(i)) {
                x2 = list.get(i + 1);
                if (x1 == -1)
                    x1 = list.get(i);
            }
        }
        return new int[]{x1, x2};
    }

    public void recover(TreeNode root, int count, int[] pair) {
        if (root == null)
            return;
        int x1 = pair[0];
        int x2 = pair[1];
        if (root.val == x1 || root.val == x2) {
            root.val = root.val == x1 ? x2 : x1;
            --count;
            if (count == 0)
                return;
        }
        recover(root.left, 1, pair);
        recover(root.right, 1, pair);
    }
}
