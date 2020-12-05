package Tree;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeOperation {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    public void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }

    @Test
    public void test1() {
        BinaryTreeOperation binaryTree = new BinaryTreeOperation();
        System.out.println(binaryTree.numTrees(3));
    }

    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        //  G(n) 和序列的内容无关，只和序列的长度有关。
        // 循环从点总数为 1 ---> n
        // G[0]
        for (int i = 2; i <= n; i++) {
            // F[j, i]: j是当前中心点，i是总数
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new LinkedList<TreeNode>();
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {

        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 迭代每个i为根的可能性
        List<TreeNode> leftTrees = null;
        List<TreeNode> rightTrees = null;
        for (int i = start; i <= end; i++) {
            // 递归每种可能性
            // 会出现start > i - 1情况，所以需要再函数初始阶段处理 start < end 的情况
            // 当前i为root，那么 [start, i-1] 为左子树，[i+1, end]为右子树
            leftTrees = generateTrees(start, i - 1);
            rightTrees = generateTrees(i + 1, end);

            // 笛卡尔拼凑左右子树
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode currRoot = new TreeNode(i);
                    currRoot.left = leftTree;
                    currRoot.right = rightTree;
                    allTrees.add(currRoot);
                }
            }
        }

        return allTrees;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p != null && q != null)
            if (p.val == q.val)
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            else return false;
        else return false;
    }
}
