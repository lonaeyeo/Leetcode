import Tree.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
//        char[][] matrix = new char[][]{
//                {'1', '0', '1', '1', '1'},
//                {'0', '1', '0', '1', '0'},
//                {'1', '1', '0', '1', '1'},
//                {'1', '1', '0', '1', '1'},
//                {'0', '1', '1', '1', '1'}};
//        System.out.println(Main.maximalRectangle(matrix));
//        int[] heights = new int[]{1,9,3,8,5};
//        System.out.println(Main.largestRectangleArea(heights));
        System.out.println(-Double.MAX_VALUE);
    }

    @Test
    public void testn() {
        TreeNode a = new TreeNode(10);
        TreeNode b = new TreeNode(5);
        TreeNode c = new TreeNode(15);
        TreeNode d = new TreeNode(6);
        TreeNode e = new TreeNode(20);
        a.left = b;
        a.right = c;
        c.left = d;
        c.right = e;
        System.out.println(a.isValidBST(a));
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode first = null;
        return first;
    }

    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxArea = 0; //最大面积
        int width = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
                    width = dp[i][j];

                    for (int k = i; k >= 0; k--) {
                        width = Math.min(width, dp[k][j]);
                        maxArea = Math.max(maxArea, width * (i - k + 1));
                    }
                }
            }
        }
        return maxArea;
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<Integer>();
        int[] lefts = new int[heights.length];
        int[] rights = new int[heights.length];
        // 假设rights所有初始值皆为8
        Arrays.fill(rights, heights.length);

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.empty() && heights[stack.peek()] >= heights[i]) {
                rights[stack.peek()] = i;
                stack.pop();
            }
            lefts[i] = stack.empty() ? -1 : stack.peek();
            stack.push(i);
        }

        for (int j = 0; j < heights.length; j++) {
            maxArea = Math.max(maxArea, heights[j] * (rights[j] - lefts[j] - 1));
        }

        return maxArea;
    }

}
