package TreeAndNode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class NextTriNodePoint {

    @Test
    public void test() {
        TriNode n1 = new TriNode(1);
        TriNode n2 = new TriNode(2);
        TriNode n3 = new TriNode(3);
        n1.left = n2;
        n1.right = n3;
        TriNode n4 = new TriNode(4);
        TriNode n5 = new TriNode(5);
        TriNode n6 = new TriNode(6);
        TriNode n7 = new TriNode(7);
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        NextTriNodePoint perfectBinaryTree = new NextTriNodePoint();
        perfectBinaryTree.connect1(n1);
    }

    // 同一层单向指向，next。完美二叉树
    public TriNode connect1(TriNode root) {
        if (root == null)
            return root;
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null)
                root.right.next = root.next.left;
        }

        connect1(root.left);
        connect1(root.right);
        return root;
    }

    // 同一层单向指向，next。普通二叉树
    public TriNode connect(TriNode root) {
        if (root == null)
            return root;

        Deque<TriNode> queue = new LinkedList<>();
        queue.offerLast(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            TriNode prev = null;

            for (int i = 0; i < len; ++i) {
                TriNode curr = queue.pollFirst();
                if (curr.left != null) queue.offerLast(curr.left);
                if (curr.right != null) queue.offerLast(curr.right);
                if (i > 0) prev.next = curr;
                prev = curr;
            }
        }
        return root;
    }

    // 普通二叉树，第二种方法，常数空间。
    TriNode last = null, nextStart = null;

    public TriNode connect_2(TriNode root) {
        if (root == null) {
            return null;
        }
        TriNode start = root;
        while (start != null) {
            // 每一层开始都要置last（prev）为空，置nextStart为空
            last = null;
            nextStart = null;
            for (TriNode p = start; p != null; p = p.next) {
                // 迭代第一个点时，将获得nextStart
                // 迭代每个点时，都会更新last
                if (p.left != null) {
                    handle(p.left);
                }
                if (p.right != null) {
                    handle(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    public void handle(TriNode p) {
        if (last != null) {
            last.next = p;
        }
        if (nextStart == null) {
            nextStart = p;
        }
        last = p;
    }
}

class TriNode {
    public int val;
    public TriNode left;
    public TriNode right;
    public TriNode next;

    public TriNode() {
    }

    public TriNode(int _val) {
        val = _val;
    }

    public TriNode(int _val, TriNode _left, TriNode _right, TriNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
