import java.util.*;
class Solution {
    public static int solution(String s) {
        ArrayDeque<Character> queue = new ArrayDeque<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            queue.add(s.charAt(i));
        }

        for (int i = 0; i < s.length(); i++) {
            if(isCorrect(queue)){
                count++;
            }
            queue.addLast(queue.pollFirst());
        }
        return count;
    }

    public static boolean isCorrect(ArrayDeque<Character> queue) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (Character ch : queue) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (ch == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else if (ch == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop(); 
            } else {
                return false; 
            }
        }
        return stack.isEmpty(); 
    }
}