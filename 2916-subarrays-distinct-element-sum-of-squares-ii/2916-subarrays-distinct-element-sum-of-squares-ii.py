from typing import List

class Solution:
    def sumCounts(self, nums: List[int]) -> int:
        MOD = 10**9 + 7
        n = len(nums)

        sum1 = [0] * (4 * n)
        sum2 = [0] * (4 * n)
        lazy = [0] * (4 * n)

        def apply(node, length, v):
            sum2[node] = (
                sum2[node] + 2 * v * sum1[node]
                + v * v * length
            ) % MOD

            sum1[node] = (sum1[node] + v * length) % MOD
            lazy[node] += v

        def push(node, start, end):
            if lazy[node] == 0 or start == end:
                return

            mid = (start + end) // 2
            v = lazy[node]

            apply(2 * node, mid - start + 1, v)
            apply(2 * node + 1, end - mid, v)

            lazy[node] = 0

        def update(node, start, end, l, r):
            if r < start or end < l:
                return

            if l <= start and end <= r:
                apply(node, end - start + 1, 1)
                return

            push(node, start, end)

            mid = (start + end) // 2

            update(2 * node, start, mid, l, r)
            update(2 * node + 1, mid + 1, end, l, r)

            sum1[node] = (
                sum1[2 * node] + sum1[2 * node + 1]
            ) % MOD

            sum2[node] = (
                sum2[2 * node] + sum2[2 * node + 1]
            ) % MOD

        last = {}
        ans = 0

        for i, x in enumerate(nums):
            prev = last.get(x, -1)

            update(1, 0, n - 1, prev + 1, i)

            ans = (ans + sum2[1]) % MOD
            last[x] = i

        return ans