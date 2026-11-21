class Solution {
    private List<Integer>[] adj;
    private List<Integer>[] newAdj;
    private int[] lastSeen;
    private int[] ans;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        adj = new ArrayList[n];
        newAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            newAdj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        dfsBuild(0, -1);

        ans = new int[n];
        dfsCount(0);

        return ans;
    }

    private void dfsBuild(int u, int p) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (u != 0) {
            int newParent = (prevAncestor != -1) ? prevAncestor : p;
            newAdj[newParent].add(u);
        }

        lastSeen[charIdx] = u;

        for (int v : adj[u]) {
            dfsBuild(v, u);
        }

        lastSeen[charIdx] = prevAncestor;
    }

    private int dfsCount(int u) {
        int size = 1;
        for (int v : newAdj[u]) {
            size += dfsCount(v);
        }
        ans[u] = size;
        return size;
    }
}