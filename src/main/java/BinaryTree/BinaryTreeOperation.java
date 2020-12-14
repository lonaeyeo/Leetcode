package BinaryTree;

import org.junit.Test;

import java.util.*;

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

    // 中序和前序
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        // 留意 length 和 length-1
        return myBuildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private Map<Integer, Integer> inorderMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
        if (preorderLeft > preorderRight)
            return null;

        int preorderRoot = preorderLeft;
        int inorderRoot = inorderMap.get(preorder[preorderRoot]);
        int leftNodeNums = inorderRoot - inorderLeft;
        TreeNode root = new TreeNode(preorder[preorderRoot]);

        // 如果此时左子树不存在，势必会造成下次递归出现 preorderLeft>preorderRight，所以需处理这种情况
        root.left = myBuildTree(preorder, inorder, preorderLeft + 1, preorderLeft + leftNodeNums, inorderLeft, inorderRoot - 1);
        root.right = myBuildTree(preorder, inorder, preorderLeft + leftNodeNums + 1, preorderRight, inorderRoot + 1, inorderRight);

        return root;
    }

    @Test
    public void testBuildTree1() {
        BinaryTreeOperation binaryTreeOperation = new BinaryTreeOperation();
        binaryTreeOperation.buildTree1(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
    }

    // 中序和后序
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        // 留意 length 和 length-1
        return myBuildTree1(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode myBuildTree1(int[] postorder, int[] inorder, int postorderLeft, int postorderRight, int inorderLeft, int inorderRight) {
        if (postorderLeft > postorderRight)
            return null;

        int postorderRoot = postorderRight;
        int inorderRoot = inorderMap.get(postorder[postorderRoot]);
        int leftNodeNums = inorderRoot - inorderLeft;
        TreeNode root = new TreeNode(inorder[inorderRoot]);

        root.left = myBuildTree1(postorder, inorder, postorderLeft, postorderLeft + leftNodeNums - 1, inorderLeft, inorderRoot - 1);
        root.right = myBuildTree1(postorder, inorder, postorderLeft + leftNodeNums, postorderRoot - 1, inorderRoot + 1, inorderRight);

        return root;
    }

    // 层序遍历，从底向上
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> results = new LinkedList<>();
        levelOrderBottomDFS(root, 1, results);
        return results;
    }

    public void levelOrderBottomDFS(TreeNode root, int depth, List<List<Integer>> results) {
        if (root == null)
            return;

        if (depth > results.size())
            // 将新的list添加至总列表的首位
            results.add(0, new ArrayList<>());

        results.get(results.size() - depth).add(root.val);
        levelOrderBottomDFS(root.left, depth + 1, results);
        levelOrderBottomDFS(root.right, depth + 1, results);
    }

    // 判断是否平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        else
            return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        else
            return Math.max(leftHeight, rightHeight) + 1;
    }

    // 最小深度
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;

        int minD = Integer.MAX_VALUE;
        if (root.left != null)
            minD = Math.min(minD, minDepth(root.left));
        if (root.right != null)
            minD = Math.min(minD, minDepth(root.right));

        return minD + 1;
    }

    // 是否存在root到叶子节点==sum的路线
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        return hasPathSumHelper(root, sum, 0);
    }

    public boolean hasPathSumHelper(TreeNode root, int sum, int currSum) {
        if (root == null)
            return false;

        if (root.val + currSum == sum)
            if (root.left == null && root.right == null)
                return true;

        // 得考虑sum是负值的可能性，所以不要剪枝
        return hasPathSumHelper(root.left, sum, root.val + currSum) || hasPathSumHelper(root.right, sum, root.val + currSum);
    }

    // 求root到叶子节点==sum的路线
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new LinkedList<>();
        if (root == null)
            return paths;

        pathSumHelper(root, sum, 0, paths);
        return paths;
    }

    public int pathSumHelper(TreeNode root, int sum, int currSum, List<List<Integer>> lists) {
        if (root == null)
            return 0;

        if (root.val + currSum == sum)
            if (root.left == null && root.right == null) {
                List<Integer> path = new LinkedList<>();
                path.add(0, root.val);
                lists.add(0, path);
                return 1;
            }

        int pathsNum = pathSumHelper(root.left, sum, root.val + currSum, lists) + pathSumHelper(root.right, sum, root.val + currSum, lists);

        for (int i = 0; i < pathsNum; i++)
            lists.get(i).add(0, root.val);

        return pathsNum;
    }

    // 二叉树展开成为链表
    public void flatten(TreeNode root) {
        if (root != null)
            flattenHelper(root);
    }

    // 获取左右子树的开头节点
    public TreeNode flattenHelper(TreeNode root) {
        if (root == null)
            return null;

        TreeNode leftT = flattenHelper(root.left);
        TreeNode rightT = flattenHelper(root.right);

        // 记得清空左子树
        root.left = null;

        if (leftT != null) {
            root.right = leftT;
            while (leftT.right != null) {
                leftT = leftT.right;
            }
        } else {
            leftT = root;
        }
        if (rightT != null)
            leftT.right = rightT;

        return root;
    }

    // 前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null)
            return ans;
        preorderTraversalHelper(root, ans);
        return ans;
    }

    public void preorderTraversalHelper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.val);
        preorderTraversalHelper(root.left, list);
        preorderTraversalHelper(root.right, list);
    }

    // 后序遍历（迭代方法）
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null)
            return list;
        Deque<TreeNode> stack = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();

        while (root != null || !stack.isEmpty()) {
            if (root == null && set.contains(stack.peek())) {
                list.add(stack.pop().val);
            } else if (root == null) {
                set.add(stack.peek());
                root = stack.peek().right;
            } else {
                stack.push(root);
                root = root.left;
            }
        }
        return list;
    }
}
