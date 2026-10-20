import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int totalSum = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            if (isSortable(nums, sorted, n, k)) {
                totalSum += k;
            }
        }

        return totalSum;
    }

    private boolean isSortable(int[] nums, int[] sorted, int n, int k) {
        for (int i = 0; i < n; i += k) {
            if (!canRotateToMatch(nums, sorted, i, k)) {
                return false;
            }
        }
        return true;
    }

    private boolean canRotateToMatch(int[] nums, int[] sorted, int start, int k) {
        if (k == 1) {
            return nums[start] == sorted[start];
        }

        int dropIndex = -1;
        for (int j = 0; j < k - 1; j++) {
            if (nums[start + j] > nums[start + j + 1]) {
                if (dropIndex != -1) {
                    return false;
                }
                dropIndex = j;
            }
        }

        int shift = (dropIndex == -1) ? 0 : dropIndex + 1;

        if (shift > 0 && nums[start + k - 1] > nums[start]) {
            return false;
        }

        for (int j = 0; j < k; j++) {
            if (nums[start + (shift + j) % k] != sorted[start + j]) {
                return false;
            }
        }

        return true;
    }
}