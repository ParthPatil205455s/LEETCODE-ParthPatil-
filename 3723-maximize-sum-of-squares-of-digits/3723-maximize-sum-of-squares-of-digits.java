class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if ((long) num * 9 < sum || sum < 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder(num);
        
        for (int i = 0; i < num; i++) {
            int take = Math.min(9, sum);
            sb.append(take);
            sum -= take;
        }

        return sb.toString();
    }
}