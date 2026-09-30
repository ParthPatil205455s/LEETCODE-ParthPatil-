class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int[seq.length()];
        for (int i = 0; i < seq.length(); i++) {
            ans[i] = seq.charAt(i) == '(' ? i & 1 : 1 - (i & 1);
        }
        return ans;
    }
}