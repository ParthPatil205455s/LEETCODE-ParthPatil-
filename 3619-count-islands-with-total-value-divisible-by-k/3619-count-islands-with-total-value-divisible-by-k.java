import java.util.*;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] > 0) {
                    long totalVal = 0;
                    Queue<int[]> queue = new ArrayDeque<>();

                    queue.offer(new int[]{r, c});
                    totalVal += grid[r][c];
                    grid[r][c] = 0;

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        for (int[] d : dirs) {
                            int nr = curr[0] + d[0];
                            int nc = curr[1] + d[1];

                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                                totalVal += grid[nr][nc];
                                grid[nr][nc] = 0;
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }

                    if (totalVal % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}