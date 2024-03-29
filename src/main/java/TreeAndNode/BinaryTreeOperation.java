package TreeAndNode;

import org.junit.Test;

import java.util.*;
import java.util.regex.Pattern;

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

    /**
     * 根据前序和中序
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        // 留意 length 和 length-1
        return myBuildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }


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

    // 第二种尝试
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTreepi(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int index, int il, int ir) {
        if (index > inorder.length - 1)
            return null;
        if (il > ir)
            return null;
        int iroot = inorderMap.get(preorder[index]);
        System.out.println(index);
        TreeNode root = new TreeNode(preorder[index]);

        root.left = buildTreeHelper(preorder, inorder, index + 1, il, iroot - 1);
        root.right = buildTreeHelper(preorder, inorder, index + iroot - il + 1, iroot + 1, ir);

        return root;
    }

    @Test
    public void testBuildTree1() {
        BinaryTreeOperation binaryTreeOperation = new BinaryTreeOperation();
        binaryTreeOperation.buildTreepi(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
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

    // 按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            int currSize = stack.size();
            for (int i = 0; i < currSize; i++) {
                TreeNode curr = stack.pollFirst();
                if (i == currSize - 1) {
                    list.add(curr.val);
                }
                if (curr.left != null) stack.offerLast(curr.left);
                if (curr.right != null) stack.offerLast(curr.right);
            }
        }

        return list;
    }

    // 求完全二叉树节点
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        // 假设level=5，bits=01000，k=1XXXX
        // 如果k=10xxx，表示节点在右子树，k=11xxx，则是左子树
        // 如果k=100xx，表示右、右；k=101xx，表示右、左；
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            // bits向右移一位
            bits >>= 1;
        }
        return node != null;
    }

    @Test
    public void test2() {
        int i = 4;
        System.out.println(i << 1);
    }

    // 最近公共祖先
    TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfsCommonAncestor(root, p, q);

        return ans;
    }

    private boolean dfsCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return false;
        boolean left = dfsCommonAncestor(root.left, p, q);
        boolean right = dfsCommonAncestor(root.right, p, q);

        if ((left && right) || ((root == p || root == q) && (left || right)))
            ans = root;
        return left || right || (root == p || root == q);
    }

    // 打家劫舍：不能打劫相邻房子
    // f表示当前节点下，包含当前节点val的sum，f(curr) = g(curr.l) + g(curr.r) + curr.val
    // g表示当前节点下，不包含当前节点val的sum，g(curr) = Max(f(curr.l), g(curr.l)) + Max(f(curr.r), g(curr.r))
    Map<TreeNode, Integer> f = new HashMap<>();
    Map<TreeNode, Integer> g = new HashMap<>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private void dfs(TreeNode root) {
        if (root == null)
            return;
        dfs(root.left);
        dfs(root.right);
        f.put(root, g.getOrDefault(root.left, 0) + g.getOrDefault(root.right, 0) + root.val);
        g.put(root, Math.max(f.getOrDefault(root.left, 0), g.getOrDefault(root.left, 0)) + Math.max(f.getOrDefault(root.right, 0), g.getOrDefault(root.right, 0)));
    }

    @Test
    public void testRob() {
//        BinaryTreeOperation binaryTreeOperation = new BinaryTreeOperation();
//        TreeNode root = binaryTreeOperation.deserialize("[3,2,3,null,3,null,1]");
//        System.out.println(binaryTreeOperation.rob(root));
        String str = " i'mastring";
        Pattern pattern = Pattern.compile("^[\\s+]");
        Pattern pattern2 = Pattern.compile("\\s+");

        System.out.println(pattern.matcher(str).find());
        System.out.println(pattern2.matcher(str).find());
        System.out.println(str);
    }

    /**
     * 根据STring构建二叉树，反序列化
     */
    public TreeNode deserialize(String data) {
        String[] nodes = data.substring(1, data.length() - 1).split(",");
        TreeNode root = getNode(nodes[0]);
        Deque<TreeNode> parents = new LinkedList();
        TreeNode parent = root;
        boolean isLeft = true;
        for (int i = 1; i < nodes.length; i++) {
            TreeNode cur = getNode(nodes[i]);
            // 先加左后加右
            if (isLeft) {
                parent.left = cur;
            } else {
                parent.right = cur;
            }
            if (cur != null) {
                parents.offerLast(cur);
            }
            isLeft = !isLeft;
            // 右子树加完，弹出当前节点
            if (isLeft) {
                parent = parents.pollFirst();
            }
        }
        return root;
    }

    private TreeNode getNode(String val) {
        if (val.equals("null")) {
            return null;
        }
        return new TreeNode(Integer.valueOf(val));
    }


    /**
     * 剑指 Offer 26. 树的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) return false;
        if (isSubStructureHelper(A, B))
            return true;
        else
            return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    // 判断A的当前开始节点和B是否有相同的结构
    private boolean isSubStructureHelper(TreeNode A, TreeNode B) {
        if (A == null && B != null) return false;
        if (B == null) return true;
        if (A.val == B.val)
            return isSubStructureHelper(A.left, B.left) && isSubStructureHelper(A.right, B.right);
        else
            return false;
    }

    /**
     * 剑指 Offer 28. 对称的二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSubStructureHelper(root.left, root.right);
    }

    public boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right != null) return false;
        if (left != null && right == null) return false;
        if (left == null && right == null) return true;
        if (left.val == right.val)
            return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
        else return false;
    }

    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     * 递归、dfs，分治成n个基本问题，判断是否true
     */
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorderHelper(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorderHelper(int[] postorder, int l, int r) {
        if (l >= r) return true;
        int p = l;
        while (postorder[p] < postorder[r]) p++;
        int m = p;
        while (postorder[p] > postorder[r]) p++;
        return p == r && verifyPostorderHelper(postorder, l, m - 1) && verifyPostorderHelper(postorder, m, r - 1);
    }

    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     * 知识点：二叉搜索树 和 中序遍历
     */
    TriNode head, pre;

    public TriNode treeToDoublyList(TriNode root) {
        if (root == null) return root;
        treeToDList(root);
        // 此时pre指向链表尾，head指向头
        pre.right = head;
        head.left = pre;
        return head;
    }

    private void treeToDList(TriNode curr) {
        if (curr == null) return;
        treeToDList(curr.left);
        // 中序遍历处理
        if (pre == null) {
            // 标记一下head
            head = curr;
        } else {
            pre.right = curr;
            curr.left = pre;
        }
        pre = curr;

        treeToDList(curr.right);
    }

    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     * 题目：包含负数，路径应为root到叶子节点
     */
    List<Integer> curr;
    List<List<Integer>> res;

    public List<List<Integer>> pathSum1(TreeNode root, int target) {
        res = new ArrayList<>();
        curr = new LinkedList<>();
        pathSumDFS(root, 0, target);
        return res;
    }

    public void pathSumDFS(TreeNode root, int sum, int target) {
        // 退出条件一：到空节点就结束
        if (root == null) return;
        int currSum = sum + root.val;
        curr.add(root.val);
        // 退出条件二：到叶子节点并且sum=target
        if (currSum == target && root.right == null && root.left == null) {
            res.add(new LinkedList<>(curr));
        } else {
            pathSumDFS(root.left, sum + root.val, target);
            pathSumDFS(root.right, sum + root.val, target);
        }
        curr.remove(curr.size() - 1);
    }

}

class Node {
    public int val;
    public TriNode left;
    public TriNode right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, TriNode _left, TriNode _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
