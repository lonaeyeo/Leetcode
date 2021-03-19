package BinaryTreeAndNode;


import java.util.*;

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

    // 给定一个sum，求满足sum的路径总数
    public int pathSum(TreeNode root, int sum) {
        // key是前缀和，value是前缀和的数量
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        return recursionPathSum(root, prefixSumMap, sum, 0);
    }

    /**
     * @param node
     * @param prefixSumMap map
     * @param target       sum
     * @param prefixSum    当前节点的前缀和
     * @return 当前节点下的满足target的路径总数
     */
    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumMap, int target, int prefixSum) {
        if (node == null)
            return 0;
        int currSum = prefixSum + node.val;
        // 满足currSum2 - currSum1(prev) = targer 即为一条路径和为target的路线
        int res = prefixSumMap.getOrDefault(currSum - target, 0);
        // 将当前节点的前缀和加入map，后续子树需用到
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);

        res += recursionPathSum(node.left, prefixSumMap, target, currSum);
        res += recursionPathSum(node.right, prefixSumMap, target, currSum);

        // 作为子树的前缀和，不应影响到父节点的计算决策
        // 也不会影响同级的兄弟子树
        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);

        return res;
    }



}
