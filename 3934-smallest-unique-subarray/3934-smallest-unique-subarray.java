import java.util.HashMap;
import java.util.Map;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long mod1 = 1_000_000_007L;
        long mod2 = 1_000_000_009L;
        long base1 = 1315423911L % mod1;
        long base2 = 2654435761L % mod2;

        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];
        long[] pref1 = new long[n + 1];
        long[] pref2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 0; i < n; i++) {
            pow1[i + 1] = (pow1[i] * base1) % mod1;
            pow2[i + 1] = (pow2[i] * base2) % mod2;
            pref1[i + 1] = (pref1[i] * base1 + nums[i]) % mod1;
            pref2[i + 1] = (pref2[i] * base2 + nums[i]) % mod2;
        }

        int left = 1, right = n, ans = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (hasUniqueSubarray(mid, n, pow1, pow2, pref1, pref2, mod1, mod2)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int len, int n, long[] pow1, long[] pow2, long[] pref1, long[] pref2, long mod1, long mod2) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int i = 0; i <= n - len; i++) {
            long h1 = (pref1[i + len] - (pref1[i] * pow1[len]) % mod1 + mod1) % mod1;
            long h2 = (pref2[i + len] - (pref2[i] * pow2[len]) % mod2 + mod2) % mod2;
            long combinedHash = (h1 << 32) | h2;
            freq.put(combinedHash, freq.getOrDefault(combinedHash, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}