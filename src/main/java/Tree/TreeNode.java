package Tree;

public class TreeNode {
    int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }



    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        if (root.left == null && root.right == null)
            return true;
        if (root.left != null && root.right == null)
            return root.left.val < root.val ? isValidBST(root.left) : false;
        if (root.right != null && root.left == null)
            return root.right.val > root.val ? isValidBST(root.right) : false;
        if (root.left.val < root.val && root.right.val > root.val)
            return isValidBST(root.left) && isValidBST(root.right);
        else
            return false;
    }

}
