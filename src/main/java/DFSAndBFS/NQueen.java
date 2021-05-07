package DFSAndBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen {
    /**
     * N皇后，回溯
     */
    ArrayList<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[] cs = new char[n];
        Arrays.fill(cs, '.');
        ArrayList<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            board.add(new String(cs));
        }
        spreadOut(board, 0);
        return res;
    }

    public void spreadOut(List<String> board, int row) {
        // 如果行到结束，添加一种答案
        if (row == board.size()) {
            res.add(new ArrayList<>(board));
            return;
        }

        // 当前行放置Q，迭代判断是否可以，若可即后续操作
        for (int i = 0; i < board.size(); i++) {
            // i是col
            if (!isValid(board, row, i))
                // 不合法就跳过
                continue;
            // 修改第row中的i列的字符为Q
            char[] temp = board.get(row).toCharArray();
            temp[i] = 'Q';
            board.remove(row);
            board.add(row, new String(temp));
            // 继续spread out
            spreadOut(board, row + 1);
            // 回溯时，需要删除改变
            temp[i] = '.';
            board.remove(row);
            board.add(row, new String(temp));
        }
    }

    private boolean isValid(List<String> board, int row, int col) {
        int n = board.size();

        // 判断同一列
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q')
                return false;
        }
        // 判断左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }
        // 判断右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }
        return true;
    }
}
