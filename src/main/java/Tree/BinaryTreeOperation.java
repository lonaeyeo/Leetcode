package Tree;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Deque;
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

    @Test
    public void test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n2.left = n4;
        n3.right = n5;
        BinaryTreeOperation binaryTreeOperation = new BinaryTreeOperation();
        binaryTreeOperation.zigzagLevelOrder1(n1);
    }

    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> lists = new ArrayList<List<Integer>>();


        if (root == null)
            return lists;

        int currLevelNum = 0;
        int flag = 1;
        List<Integer> list1 = new LinkedList<>();
        list1.add(root.val);
        lists.add(list1);
        queue.offerLast(root);

        while (!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            currLevelNum = queue.size();
            while (currLevelNum != 0) {
                TreeNode curr = queue.pollFirst();
                if (curr.left != null) {
                    // 判断从左到右，还是从右到左（flag为0表示从左到右）
                    if (flag == 0) list.addLast(curr.left.val);
                    else list.addFirst(curr.left.val);
                    queue.offerLast(curr.left);
                }
                if (curr.right != null) {
                    // 判断从左到右，还是从右到左（flag为0表示从左到右）
                    if (flag == 0) list.addLast(curr.right.val);
                    else list.addFirst(curr.right.val);
                    queue.offerLast(curr.right);
                }
                --currLevelNum;
            }
            flag = flag == 0 ? 1 : 0;
            if (list.isEmpty())
                continue;
            lists.add(list);
        }

        return lists;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> results = new ArrayList<List<Integer>>();

        // add the root element with a delimiter to kick off the BFS loop
        // node_queue 始终从左到右
        LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
        node_queue.addLast(root);
        node_queue.addLast(null);

        // level_list 看情况
        LinkedList<Integer> level_list = new LinkedList<Integer>();
        boolean is_order_left = true;

        while (node_queue.size() > 0) {
            TreeNode curr_node = node_queue.pollFirst();
            if (curr_node != null) {
                if (is_order_left)
                    level_list.addLast(curr_node.val);
                else
                    level_list.addFirst(curr_node.val);

                if (curr_node.left != null)
                    node_queue.addLast(curr_node.left);
                if (curr_node.right != null)
                    node_queue.addLast(curr_node.right);

            } else {
                // we finish the scan of one level
                results.add(level_list);
                level_list = new LinkedList<Integer>();
                // prepare for the next level
                if (node_queue.size() > 0)
                    node_queue.addLast(null);
                is_order_left = !is_order_left;
            }
        }
        return results;
    }

}
