package BinaryTree;

import sun.reflect.generics.tree.Tree;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

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

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
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

    // 二叉搜索树的最大公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val > p.val && root.val > q.val)
                root = root.left;
            else if (root.val < p.val && root.val < q.val)
                root = root.right;
            else
                return root;
        }
    }

    // 二叉搜索树的序列化及反序列化
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        if (sb.length() > 0)
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // 利用后序遍历[左左左…… 右右右…… 中]
    private void postOrder(TreeNode root, StringBuilder sb) {
        if (root == null)
            return;

        postOrder(root.left, sb);
        postOrder(root.right, sb);

        sb.append(root.val);
        sb.append(' ');
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty())
            return null;
        ArrayDeque<Integer> nums = new ArrayDeque<>();
        for (String s : data.split("\\s+")) {
            nums.add(Integer.valueOf(s));
        }
        return numsToNodes(nums, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    private TreeNode numsToNodes(ArrayDeque<Integer> nums, int low, int high) {
        if (nums.size() == 0)
            return null;
        int currNum = nums.getLast();
        if (currNum < low || currNum > high)
            return null;
        nums.removeLast();
        TreeNode curr = new TreeNode(currNum);
        curr.right = numsToNodes(nums, currNum, high);
        curr.left = numsToNodes(nums, low, currNum);
        return curr;
    }

}
