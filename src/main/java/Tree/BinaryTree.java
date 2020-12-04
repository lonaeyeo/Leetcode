package Tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryTree {

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
        BinaryTree binaryTree = new BinaryTree();
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

}
