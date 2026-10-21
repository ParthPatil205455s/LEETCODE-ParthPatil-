class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        
        if (n <= 2) return -1;
        
        char mn = 'z';
        char mx = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < mn) mn = c;
            if (c > mx) mx = c;
        }
        
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) {
            return 1;
        }
        
        int countMn = 0;
        int countMx = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == mn) countMn++;
            if (c == mx) countMx++;
        }
        
        if (s.charAt(0) == mx && countMx == 1 && s.charAt(n - 1) == mn && countMn == 1) {
            return 3;
        }
        
        return 2;
    }
}