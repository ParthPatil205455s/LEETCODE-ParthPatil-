class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        int[][][] dest = new int[m][n][2];

        for (int s = m + n - 2; s >= 0; s--) {
            for (int r = 0; r < m; r++) {
                int c = s - r;
                if (c < 0 || c >= n) continue;

                for (int d = 0; d < 2; d++) {
                    if (grid[r][c] == 0) {
                        dest[r][c][d] = r * n + c;
                    } else {
                        if (d == 1) {
                            if (r + 1 < m) {
                                dest[r][c][d] = dest[r + 1][c][0];
                            } else {
                                dest[r][c][d] = -1;
                            }
                        } else {
                            if (c + 1 < n) {
                                dest[r][c][d] = dest[r][c + 1][1];
                            } else {
                                dest[r][c][d] = -1;
                            }
                        }
                    }
                }
            }
        }

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int s = 0; s < m + n - 1; s++) {
            for (int r = 0; r < m; r++) {
                int c = s - r;
                if (c < 0 || c >= n || dp[r][c] == 0) continue;

                int val = dp[r][c];

                if (c + 1 < n) {
                    int target = dest[r][c + 1][1];
                    if (target != -1) {
                        int nr = target / n;
                        int nc = target % n;
                        dp[nr][nc] = (dp[nr][nc] + val) % MOD;
                    }
                }

                if (r + 1 < m) {
                    int target = dest[r + 1][c][0];
                    if (target != -1) {
                        int nr = target / n;
                        int nc = target % n;
                        dp[nr][nc] = (dp[nr][nc] + val) % MOD;
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}