package Tree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class PerfectBinaryTree {

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
        PerfectBinaryTree perfectBinaryTree = new PerfectBinaryTree();
        perfectBinaryTree.connect(n1);
    }

    public Node connect(Node root) {
        if (root == null)
            return root;
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null)
                root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
        return root;
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
