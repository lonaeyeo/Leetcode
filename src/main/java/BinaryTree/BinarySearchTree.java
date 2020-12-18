package BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    List<Integer> nodesSorted;
    int index;

    public BinarySearchTree(TreeNode root) {
        this.nodesSorted = new ArrayList<>();
        this.index = -1;
        this.inOrder(root);
    }

    public void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        nodesSorted.add(root.val);
        inOrder(root.right);
    }

    public int next() {
        return this.nodesSorted.get(++this.index);
    }

    public boolean hasNext() {
        return this.index + 1 < this.nodesSorted.size();
    }
}
