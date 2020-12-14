package BinaryTree;

public class BinarySearchTree {

    public void recoverTree(TreeNode root) {
        TreeNode max = new TreeNode(Integer.MAX_VALUE);
        TreeNode min = new TreeNode(Integer.MIN_VALUE);
        recoverTreeHelper(root, min, max);
    }

    public void recoverTreeHelper(TreeNode root, TreeNode lower, TreeNode upper) {
        if (root == null)
            return;
        if (root.val < lower.val) {
            swapValue(root, lower);
            return;
        } else if (root.val > upper.val) {
            swapValue(root, upper);
            return;
        }

        recoverTreeHelper(root.left, lower, root);
        recoverTreeHelper(root.right, root, upper);
    }

    public void swapValue(TreeNode n1, TreeNode n2) {
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }
}
