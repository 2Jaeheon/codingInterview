package stack;

import java.util.ArrayDeque;

public class 짝지어_제거하기 {

    public static void main(String[] args) {
        String s1 = "baabaa";
        String s2 = "cdcd";
        String s3 = "abcda";
        String s4 = "abcdee";

        System.out.println(solution2(s1));
        System.out.println(solution2(s2));
        System.out.println(solution2(s3));
        System.out.println(solution2(s4));
    }

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

        System.out.println(stack);
        if (stack.isEmpty()) {
            return 1;
        }
        return 0;
    }

    public static int solution2(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // 스택이 비어있는 경우
            if (stack.isEmpty()) {
                stack.push(ch);  // 스택이 비어있으면 현재 문자를 푸시
            } else {  // 스택이 비어있지 않은 경우
                if (stack.peek() == ch) {
                    stack.pop();  // 스택의 맨 위 문자와 현재 문자가 동일하면 제거
                } else {
                    stack.push(ch);  // 스택의 맨 위 문자와 현재 문자가 다르면 푸시
                }
            }
        }
        if (stack.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }
}
