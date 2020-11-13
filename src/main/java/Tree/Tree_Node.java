package Tree;

public class Tree_Node {
    int val;
    public Tree_Node left;
    public Tree_Node right;
    public Tree_Node(int x) { val = x; }



    public boolean isValidBST(Tree_Node root) {
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
