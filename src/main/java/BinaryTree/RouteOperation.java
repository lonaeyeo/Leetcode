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

}
