import java.util.*;
class Solution {
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
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
}