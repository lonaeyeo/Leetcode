package TreeAndNode;

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

    // 删除二叉搜索树中的节点
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return root;
        // prev为待删节点的父亲树
        TreeNode prev = new TreeNode();
        TreeNode curr = root;
        prev.left = root;
        // 标记删除的是左子树还是右子树，0为表示待删节点curr位于prev的左子树
        int flag = 0;
        // 判断删除的点是第几层的
        int index = 0;

        while (curr.val != key) {
            prev = curr;
            if (curr.val < key) {
                flag = 1;
                curr = curr.right;
            } else if (curr.val > key) {
                flag = 0;
                curr = curr.left;
            }
            ++index;
            if (curr == null) return root;
        }

        // 如果删除的是根节点，需调正root的指向，防止原有root指向消失
        if (index == 0) {
            // 如果根节点的左空右不空那么root需要指向根节点的右子树
            if (curr.left == null && curr.right != null)
                root = curr.right;
            else
                root = curr.left;
        }

        // 如果待删节点无子节点
        if (curr.left == null && curr.right == null) {
            prev.left = flag == 0 ? null : prev.left;
            prev.right = flag == 1 ? null : prev.right;
            return root;
            // 如果待删节点的左子树不存在
        } else if (curr.left == null) {
            prev.left = flag == 0 ? curr.right : prev.left;
            prev.right = flag == 1 ? curr.right : prev.right;
            return root;
            // 如果待删节点的左子树存在（右子树可能存在，可能不存在）
        } else {
            prev.left = flag == 0 ? curr.left : prev.left;
            prev.right = flag == 1 ? curr.left : prev.right;
            // 原右子树
            TreeNode currRight = curr.right;
            curr = curr.left;
            while (curr.right != null) {
                curr = curr.right;
            }
            curr.right = currRight;
        }

        return root;
    }


    /**
     * 剑指 Offer 27. 二叉树的镜像
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> stack = new LinkedList<TreeNode>() {{
            push(root);
        }};

        while (!stack.isEmpty()) {
            // 每次只针对一个Node处理
            TreeNode curr = stack.pop();
            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
        }
        return root;

        //
        // return dfsHelper(root);
    }

    private TreeNode dfsHelper(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = dfsHelper(root.right);
        TreeNode right = dfsHelper(root.left);
        root.left = left;
        root.right = right;
        return root;
    }
}
