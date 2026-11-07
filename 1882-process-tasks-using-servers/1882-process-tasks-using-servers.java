import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }

        long time = 0;
        for (int j = 0; j < m; j++) {
            time = Math.max(time, (long) j);

            if (freeServers.isEmpty() && !busyServers.isEmpty()) {
                time = Math.max(time, busyServers.peek()[0]);
            }

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= time) {
                long[] curr = busyServers.poll();
                freeServers.offer(new int[]{(int) curr[1], (int) curr[2]});
            }

            int[] server = freeServers.poll();
            ans[j] = server[1];
            busyServers.offer(new long[]{time + tasks[j], (long) server[0], (long) server[1]});
        }

        return ans;
    }
}