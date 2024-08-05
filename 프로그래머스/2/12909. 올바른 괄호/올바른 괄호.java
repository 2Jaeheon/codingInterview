import java.util.*;
class Solution {
    static boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        int top = -1;

        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                if (stack.peek() == '(') {
                    if (s.charAt(i) == ')') {
                        stack.pop();
                    } else {
                        stack.push(s.charAt(i));
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

}