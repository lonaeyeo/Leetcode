package BinaryTree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RouteOperation {

    // 求根到叶子节点数字之和
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    private int sumNumbers(TreeNode root, int currSum) {
        if (root == null)
            return 0;
        currSum = currSum * 10 + root.val;
        if (root.left == null && root.right == null)
            return currSum;
        else
            return sumNumbers(root.left, currSum) + sumNumbers(root.right, currSum);
    }

    // 任意一点到任意点最大路径
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        maxBranch(root);
        return maxSum;
    }

    // 计算当前节点的左右子树中的最大分枝和，例如root=1，left=10，right=1，那么最大分枝和就是1+10=11
    private int maxBranch(TreeNode root) {
        if (root == null)
            return 0;
        int leftBranch = Math.max(maxBranch(root.left), 0);
        int rightBranch = Math.max(maxBranch(root.right), 0);

        int currSum = 0;
        // 计算当前节点的最大路径，并与全局maxSum比较，已去除branch小于0的情况
        currSum = root.val + leftBranch + rightBranch;
        maxSum = Math.max(currSum, maxSum);

        return root.val + Math.max(leftBranch, rightBranch);
    }


}
