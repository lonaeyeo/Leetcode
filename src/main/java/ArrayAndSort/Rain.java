package ArrayAndSort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Rain {
    /**
     * 接雨水
     * 从外围往里算，最开始是木桶的短板
     * 特殊数据结构PriorityQueue，可排序
     */
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int res = 0;
        boolean[][] visited = new boolean[m][n];
        // int[] = {x, y, height}，按height从小到大排序
        PriorityQueue<int[]> wall = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // 把最外层先添加进去
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    wall.offer(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }

        while (!wall.isEmpty()) {
            // 弹出当前高度最小值的块
            int[] curr = wall.poll();
            // x+dirs[0], y+dirs[1]向上，以此类推
            int[] dirs = new int[]{-1, 0, 1, 0, -1};
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dirs[i];
                int ny = curr[1] + dirs[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    if (heightMap[nx][ny] < curr[2]) {
                        res += curr[2] - heightMap[nx][ny];
                    }
                    visited[nx][ny] = true;
                    // 添加进wall的新块的高度取max（）
                    wall.offer(new int[]{nx, ny, Math.max(heightMap[nx][ny], curr[2])});
                }
            }
        }

        return res;
    }
}
