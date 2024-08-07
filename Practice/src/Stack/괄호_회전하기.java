package Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

public class 괄호_회전하기 {

    public static void main(String[] args) {
        String s1 = "[](){}";
        String s2 = "}]()[{";
        String s3 = "[)(]";
        String s4 = ")))";

        System.out.println(solution2(s1));
        System.out.println(solution2(s2));
        System.out.println(solution2(s3));
        System.out.println(solution2(s4));
    }

    public static int solution(String s) {
        ArrayDeque<Character> queue = new ArrayDeque<>();
        int count = 0;
        //문자열의 개수만큼 반복
        for (int i = 0; i < s.length(); i++) {
            queue.add(s.charAt(i));
        }

        for (int i = 0; i < s.length(); i++) {
            if (isCorrect(queue)) {
                count++;
            }
            queue.addLast(queue.pollFirst());
        }
        return count;
    }

    public static boolean isCorrect(ArrayDeque<Character> queue) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        //ch는 괄호 하나를 나타냄
        for (Character ch : queue) {
            //스택이 비어있을 때
            if (stack.isEmpty()) {
                stack.push(ch);
            } else {
                //스택이 비어있지 않을 때
                if (ch == '(' || ch == '[' || ch == '{') {
                    stack.push(ch);
                } else if (ch == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (ch == ']' && stack.peek() == '[') {
                    stack.pop();
                } else if (ch == '}' && stack.peek() == '{') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    public static int solution2(String str) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        int len = str.length();
        str += str;
        int answer = 0;

        A:
        for (int i = 0; i < len; i++) {
            ArrayDeque<Character> stack = new ArrayDeque<>();

            //i부터 원본 문자열의 길이가 n개까지 올바른 괄호 문자열인지 확인
            for (int j = i; j < i + len; j++) {
                char c = str.charAt(j);

                //해시맵 안에 해당 키가 없다면 열리는 괄호
                if (!map.containsKey(c)) {
                    stack.push(c);
                } else {
                    if (stack.isEmpty() || !stack.pop().equals(map.get(c))) {
                        continue A;
                    }
                }
            }

            if (stack.isEmpty()) {
                answer++;
            }
        }
        return answer;
    }
}
