package BinarySearch;

import java.util.*;

public class MinimumEffortsUsagePath {

    /**
     * 最小体力消耗路径
     * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
     * 第一种方法：二分搜索+BFS
     * 第二中方法：最短路径
     */

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        int left = 0;
        int right = 999999;
        int ans = 0;

        // 二分搜索
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 初始化
            Deque<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0});
            boolean[] seen = new boolean[rows * cols];
            seen[0] = true;
            // 广度优先搜索
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                // 访问四个方向，获取下个点位置（东、西、南、北）
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + dirs[i][0];
                    int y = curr[1] + dirs[i][1];
                    // 每一个点都会被访问到
                    // 判断该点有无过界、是否已访问过，消耗是否不超过mid
                    if (x >= 0 && x < rows && y >= 0 && y < cols && !seen[x * cols + y] && Math.abs(heights[x][y] - heights[curr[0]][curr[1]]) <= mid) {
                        queue.offer(new int[]{x, y});
                        seen[x * cols + y] = true;
                    }
                }
            }
            // 二分搜索，如果所有点都放问过了，说明这个消耗mid是可以的，那么right=mid-1，否则left=mid+1
            if (seen[rows * cols - 1]) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    // 迪杰斯特拉
    public int minimumEffortPath2(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });
        pq.offer(new int[]{0, 0, 0});

        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] seen = new boolean[m * n];

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0], y = edge[1], d = edge[2];
            int id = x * n + y;
            if (seen[id]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                break;
            }
            seen[id] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.abs(heights[x][y] - heights[nx][ny])) < dist[nx * n + ny]) {
                    dist[nx * n + ny] = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                    pq.offer(new int[]{nx, ny, dist[nx * n + ny]});
                }
            }
        }
        return dist[m * n - 1];
    }


}
