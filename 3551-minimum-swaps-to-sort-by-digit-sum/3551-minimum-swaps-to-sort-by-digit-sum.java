import java.util.Arrays;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][3];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = getDigitSum(nums[i]);
            arr[i][2] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int[] targetPos = new int[n];
        for (int i = 0; i < n; i++) {
            targetPos[arr[i][2]] = i;
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || targetPos[i] == i) {
                continue;
            }

            int cycleSize = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = targetPos[curr];
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }

    private int getDigitSum(int val) {
        int sum = 0;
        while (val > 0) {
            sum += val % 10;
            val /= 10;
        }
        return sum;
    }
}