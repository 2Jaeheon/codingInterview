package Stack;

import java.util.ArrayDeque;
import java.util.Collections;

public class 십진수를_이진수로 {

    public static void main(String[] args) {
        int d1 = 10; //1010
        int d2 = 27; //11011
        int d3 = 12345; //11000000111001

        System.out.println(solution(d1));
        System.out.println(solution(d2));
        System.out.println(solution(d3));
    }

    public static String solution(int decimal) {
        int remainder = decimal;
        String answer = "";
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        while (true) {
            if (remainder == 0) {
                break;
            } else {
                stack.push(remainder % 2);
                remainder = remainder / 2;
            }
        }
        //String의 +연산은 속도가 매우 느리므로 StringBuilder를 사용하자
        /*
        while (!stack.isEmpty()) {
            answer += stack.pop();
        }*/
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
