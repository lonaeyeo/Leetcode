package DFSAndBFS;

import java.util.Deque;
import java.util.LinkedList;

public class Robot {

    /**
     * 剑指 13，机器人的运动范围
     * k，数位之和<k
     * 例如：[12,33],1+2+3+3=9<k?
     * BFS
     */
    public int movingCount(int m, int n, int k) {
        if (k == 0)
            return 1;
        Deque<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        int[] toR = new int[]{0, 1, 0};
        int ans = 1;
        visited[0][0] = true;
        queue.offer(new int[]{0, 0});

        // BFS
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            // toRight, downward
            for (int i = 0; i < 2; i++) {
                int x = curr[0] + toR[i];
                int y = curr[1] + toR[i + 1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || getV(x) + getV(y) > k)
                    continue;
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
                ans++;
            }
        }

        return ans;
    }

    private int getV(int x) {
        int temp = 0;
        while (x > 0) {
            temp += x % 10;
            x /= 10;
        }
        return temp;
    }
}
