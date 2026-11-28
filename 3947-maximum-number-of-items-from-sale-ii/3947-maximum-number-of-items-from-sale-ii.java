import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] gain = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int count = freq[i];

            for (int j = i * 2; j <= n; j += i) {
                count += freq[j];
            }

            gain[i] = count - 1;
        }

        List<int[]> deals = new ArrayList<>();

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            if (gain[factor] > 0 && (long) price < 2L * minPrice) {
                deals.add(new int[]{price, gain[factor]});
            }
        }

        deals.sort((a, b) -> Integer.compare(a[0], b[0]));

        long ans = 0;
        long remaining = budget;

        for (int[] deal : deals) {
            int price = deal[0];
            int count = deal[1];

            long buy = Math.min(count, remaining / price);

            ans += 2L * buy;
            remaining -= buy * price;
        }

        ans += remaining / minPrice;

        return (int) ans;
    }
}