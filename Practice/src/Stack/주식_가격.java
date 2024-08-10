package Stack;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;

public class 주식_가격 {

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 2, 3};
        int[] prices2 = new int[]{3, 2, 1, 4, 5, 3};
        int[] prices3 = new int[]{3, 1, 3, 1, 3};
        int[] prices4 = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(solution2(prices)));
        System.out.println(Arrays.toString(solution2(prices2)));
        System.out.println(Arrays.toString(solution2(prices3)));
        System.out.println(Arrays.toString(solution2(prices4)));
    }

    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int timer = 0;

        for (int i = 0; i < answer.length; i++) {
            for (int j = i + 1; j < answer.length; j++) {
                if (prices[i] > prices[j]) {
                    timer++;
                    break;
                } else {
                    timer++;
                }
            }
            answer[i] = timer;
            timer = 0;
        }
        return answer;
    }

    public static int[] solution2(int[] prices) {
        //한 번도 가격이 떨어지지 않은 주식을 저장하는 스택
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int n = prices.length;
        //가격이 떨어지지 않은 기간을 저장할 배열
        int[] answer = new int[n];

        stack.push(0);

        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                //가격이 떨어졌기 때문에 이전 가격의 기간 계산
                int j = stack.pop();
                answer[j] = i - j;
            }
            stack.push(i);
        }

        //스택에 남아있는 가격들은 가격이 떨어지지 않은 경우
        while (!stack.isEmpty()) {
            int j = stack.pop();
            answer[j] = n - 1 - j;
        }
        return answer;
    }
}
