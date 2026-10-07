import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        int leftMisplaced = 0;
        int rightMisplaced = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftMisplaced++;
            } else if (c == ')') {
                if (leftMisplaced > 0) {
                    leftMisplaced--;
                } else {
                    rightMisplaced++;
                }
            }
        }
        
        dfs(s, 0, leftMisplaced, rightMisplaced, result);
        return result;
    }
    
    private void dfs(String s, int start, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            if (i != start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            
            if (left > 0 && s.charAt(i) == '(') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, left - 1, right, result);
            }
            
            if (right > 0 && s.charAt(i) == ')') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, left, right - 1, result);
            }
        }
    }
    
    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
}