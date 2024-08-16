import java.util.*;
class Solution {
    public static int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        // 모든 작업을 큐에 추가
        for (int i = 0; i < progresses.length; i++) {
            //올림
            int daysRequired = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            queue.add(daysRequired);
        }


        while (!queue.isEmpty()) {
            int current = queue.poll();
            int count = 1;

            // 현재 작업과 함께 배포될 수 있는 작업들을 묶어서 배포
            while (!queue.isEmpty() && queue.peek() <= current) {
                queue.poll();
                count++;
            }

            result.add(count);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}