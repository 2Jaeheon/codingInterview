package stack;

import java.util.*;

public class 올바른_괄호 {

    public static void main(String[] args) {
        String s1 = "()()";
        String s2 = "(())()";
        String s3 = "(((())()";

        System.out.println(solution(s1));
        System.out.println(solution(s2));
        System.out.println(solution(s3));

    }

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

    static boolean solution2(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        char[] a = s.toCharArray();
        for (char c : a) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty() || stack.pop() == c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
