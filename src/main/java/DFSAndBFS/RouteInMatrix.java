package DFSAndBFS;

import org.junit.Test;

public class RouteInMatrix {

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * DFS
     */

    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        if (n * m < word.length())
            return false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(board, i, j, word, 0))
                    return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, int x, int y, String word, int curr) {
        if (x >= board.length || x < 0 || y >= board[0].length || y < 0 || board[x][y] != word.charAt(curr))
            return false;
        if (word.length() - 1 == curr) return true;

        //System.out.print(x + "," + y + ": ");
        //System.out.println(board[x][y]);
        board[x][y] = '\0';
        boolean res = dfs(board, x + 1, y, word, curr + 1) || dfs(board, x, y + 1, word, curr + 1) || dfs(board, x - 1, y, word, curr + 1) || dfs(board, x, y - 1, word, curr + 1);
        board[x][y] = word.charAt(curr);
        return res;
    }

    @Test
    public void test() {
//        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        char[][] board = new char[][]{{'a', 'a'}};
        String word = "aaa";
        System.out.println(exist(board, word));
    }
}
