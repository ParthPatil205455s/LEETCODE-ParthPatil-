import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] sq = new long[n];
        
        for (int i = 0; i < n; i++) {
            sq[i] = (long) nums[i] * nums[i];
        }
        
        Arrays.sort(sq);
        
        long ans = 0;
        int negCount = n / 2;
        
        for (int i = 0; i < negCount; i++) {
            ans -= sq[i];
        }
        
        for (int i = negCount; i < n; i++) {
            ans += sq[i];
        }
        
        return ans;
    }
}