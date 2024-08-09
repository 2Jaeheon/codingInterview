import java.util.*;
class Solution
{
     public static int solution(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        if (stack.isEmpty()) {
            return 1;
        }
        return 0;
    }
}