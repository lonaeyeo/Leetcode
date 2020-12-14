package Tree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class NextNodePoint {

    @Test
    public void test() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.left = n2;
        n1.right = n3;
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        NextNodePoint perfectBinaryTree = new NextNodePoint();
        perfectBinaryTree.connect1(n1);
    }

    // 同一层单向指向，next。完美二叉树
    public Node connect1(Node root) {
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
    public Node connect(Node root) {
        if (root == null)
            return root;

        Deque<Node> queue = new LinkedList<>();
        queue.offerLast(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            Node prev = null;

            for (int i = 0; i < len; ++i) {
                Node curr = queue.pollFirst();
                if (curr.left != null) queue.offerLast(curr.left);
                if (curr.right != null) queue.offerLast(curr.right);
                if (i > 0) prev.next = curr;
                prev = curr;
            }
        }
        return root;
    }

    // 普通二叉树，第二种方法，常数空间。
    Node last = null, nextStart = null;

    public Node connect_2(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while (start != null) {
            // 每一层开始都要置last（prev）为空，置nextStart为空
            last = null;
            nextStart = null;
            for (Node p = start; p != null; p = p.next) {
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

    public void handle(Node p) {
        if (last != null) {
            last.next = p;
        }
        if (nextStart == null) {
            nextStart = p;
        }
        last = p;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
