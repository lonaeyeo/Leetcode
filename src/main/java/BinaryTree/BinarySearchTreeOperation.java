package BinaryTree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTreeOperation {

    List<Integer> nodesSorted;
    int index;

    public BinarySearchTreeOperation(TreeNode root) {
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

    // 第k小的元素
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();

        while(!stack.isEmpty() || root != null){
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode curr = stack.pop();
            --k;
            if (k == 0) return curr.val;
            root = root.right;
        }

        return root.val;
    }
}
