class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long totalOps = (long) k1 + k2;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            if (diff[i] > maxDiff) {
                maxDiff = diff[i];
            }
        }

        int[] count = new int[maxDiff + 1];
        for (int d : diff) {
            count[d]++;
        }

        for (int d = maxDiff; d > 0 && totalOps > 0; d--) {
            if (count[d] == 0) {
                continue;
            }

            long take = Math.min((long) count[d], totalOps);
            count[d] -= (int) take;
            count[d - 1] += (int) take;
            totalOps -= take;

            if (count[d] > 0) {
                break;
            }
        }

        long result = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                result += (long) count[d] * (long) d * d;
            }
        }

        return result;
    }
}