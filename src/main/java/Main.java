import BinaryTree.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
//        for (int i = 1; i <= 200; i++) {
//            if ((i - 1) % 20 == 0)
//                System.out.println();
//            System.out.print(",b" + i);
//        }
//        for (int i = 1; i <= 50; i++) {
//            if ((i - 1) % 5 == 0)
//                System.out.println();
//            System.out.print(",x" + i);
//        }

        for (int i = 0; i < 10; i++) {
            System.out.println("inner-outer(x" + (1 + i * 5) + ", x" + (2 + i * 5) + ");");
            System.out.println("inner-outer(x" + (5 + i * 5) + ", x" + (4 + i * 5) + ");");
            System.out.println("outer-inner(x" + (2 + i * 5) + ", x" + (1 + i * 5) + ");");
            System.out.println("outer-inner(x" + (4 + i * 5) + ", x" + (5 + i * 5) + ");");
            System.out.println();
        }


    }

    @Test
    public void testbin() {
        int bn = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j <= 5; j++) {
                if (j == 3) continue;
                System.out.println("        binAt(b" + (bn++) + ", l" + (1 + i) + ", x" + j + ", y2, z0);");
                System.out.println("        binAt(b" + (bn++) + ", l" + (1 + i) + ", x" + j + ", y3, z0);");
                System.out.println("        binAt(b" + (bn++) + ", l" + (1 + i) + ", x" + j + ", y4, z0);");
                System.out.println("        binAt(b" + (bn++) + ", l" + (1 + i) + ", x" + j + ", y5, z0);");
                System.out.println("        binAt(b" + (bn++) + ", l" + (1 + i) + ", x" + j + ", y6, z0);");
            }
            System.out.println();
        }
    }

    @Test
    public void testaisle() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 7; j++) {
                System.out.println("        aisleAt(l" + i + ", x" + (3 + 5 * (i - 1)) + ", y" + j + ");");
            }
        }
    }

    @Test
    public void testStacker() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("        stacker(s" + i + ", l" + i + ");");
        }
    }

    @Test
    public void testAdjecent() {
        for (int i = 0; i < 10; i++) {
            System.out.println("        adjacent(x" + (3 + i * 5) + ", x" + (5 + i * 5) + ");");
            System.out.println("        adjacent(x" + (5 + i * 5) + ", x" + (3 + i * 5) + ");");
            System.out.println("        inboundBase(x" + (5 + i * 5) + ", y" + (1 + i * 5) + ");");
            System.out.println("        outboundBase(x" + (5 + i * 5) + ", y" + (7 + i * 5) + ");");
            System.out.println();
        }
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

    @Test
    public void stackerAt() {
        for (int i = 0; i < 10; i++) {
            System.out.println("        stackerAt(s" + (1 + i) + ", x" + (5 + i * 5) + ", y1);");
        }
    }

    @Test
    public void itemAt() {
        for (int i = 0; i < 10; i++) {
            System.out.println("        itemAt(x" + (1 + i * 5) + ", y3, z0);");
            System.out.println("        itemAt(x" + (2 + i * 5) + ", y4, z0);");
            System.out.println("        itemAt(x" + (4 + i * 5) + ", y5, z0);");
            System.out.println("        outboundItemAt(x" + (2 + i * 5) + ", y4, z0);");
        }
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
