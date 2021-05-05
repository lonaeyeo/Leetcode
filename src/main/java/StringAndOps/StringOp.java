package StringAndOps;

import Tree.ListNode;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class StringOp {

    /**
     * 剑指05.替换空格
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            } else {
                sb.append("%20");
            }
        }
        return sb.toString();
    }

    /**
     * 剑指06.从尾到头打印链表
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[0];
        List<Integer> list = new ArrayList<>();
        rPrint(head, list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public void rPrint(ListNode head, List<Integer> list) {
        if (head != null) {
            return;
        }
        rPrint(head.next, list);
        list.add(head.val);
    }

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     */
    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return new int[0];
        int n = matrix[0].length;
        if (n == 0) return new int[0];
        int[] res = new int[m * n];
        boolean[][] vis = new boolean[m][n];
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int currDir = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < res.length; i++) {
            res[i] = matrix[x][y];
            vis[x][y] = true;

            int nx = x + dirs[currDir][0];
            int ny = y + dirs[currDir][1];
            // 判断下一个方向
            if (nx < 0 || nx >= m || ny < 0 || ny >= n || vis[nx][ny]) {
                // 方向变化，调整方向
                currDir = (currDir + 1) % 4;
            }
            x = x + dirs[currDir][0];
            y = y + dirs[currDir][1];
        }

        return res;
    }


    /**
     * 剑指 Offer 20. 表示数值的字符串
     */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0)
            return false;
        // 去空
        s = s.trim();

        try {
            float tmp = Float.parseFloat(s);
        } catch (Exception e) {
            return false;
        }
        // 末尾有f，d,D这些不算，但是3.算数字（面向测试用例编程）
        char c = s.charAt(s.length() - 1);
        return Character.isDigit(c) || c == '.';
    }

}
