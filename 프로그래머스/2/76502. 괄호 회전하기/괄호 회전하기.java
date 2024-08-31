import java.util.*;
class Solution {
    public int solution(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int answer = 0;
        
        for (int start = 0; start < n; start++) {
            if (isValid(chars, start, n)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isValid(char[] chars, int start, int n) {
        int[] stack = new int[n];
        int top = -1;
        
        for (int i = 0; i < n; i++) {
            char c = chars[(start + i) % n];
            if (c == '(' || c == '{' || c == '[') {
                stack[++top] = c;
            } else {
                if (top == -1) return false;
                if (c == ')' && stack[top] != '(') return false;
                if (c == '}' && stack[top] != '{') return false;
                if (c == ']' && stack[top] != '[') return false;
                top--;
            }
        }
        
        return top == -1;
    }
}