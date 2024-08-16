package queue.기능개발;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class 기능_개발 {

    public static void main(String[] args) {
        int[] progress1 = new int[]{93, 30, 55};
        int[] progress2 = new int[]{95, 90, 99, 99, 80, 99};

        int[] speed1 = new int[]{1, 30, 5};
        int[] speed2 = new int[]{1, 1, 1, 1, 1, 1};

        System.out.println(Arrays.toString(solution2(progress1, speed1)));
        System.out.println(Arrays.toString(solution2(progress2, speed2)));
    }

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
            System.out.println(queue);
            int count = 1;

            // 현재 작업과 함께 배포될 수 있는 작업들을 묶어서 배포
            while (!queue.isEmpty() && queue.peek() <= current) {
                queue.poll();
                count++;
            }

            result.add(count);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] solution2(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        int day = 0;
        int index = 0;

        while (index < progresses.length) {
            //날을 하루씩 증가시키면서 문제를 해결
            day++;

            //반복을 돌려서 100퍼센트를 넘은 인덱스를 큐에 저장
            while (index < progresses.length && progresses[index] + speeds[index] * day >= 100) {
                queue.add(index);
                index++;
            }

            //연속적으로 큐에 담겨있기 때문에 반복시켜서 큐의 모든 것들을 꺼내서 개수를 세서 리스트에 담는다.
            if (!queue.isEmpty()) {
                int count = 0;
                while (!queue.isEmpty()) {
                    queue.poll();
                    count++;
                }
                result.add(count);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static int[] solution4(int[] progresses, int[] speeds) {
        Queue<Integer> answer = new ArrayDeque<>();

        int n = progresses.length;
        int[] daysLeft = new int[n];
        for (int i = 0; i < n; i++) {
            daysLeft[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }
        int count = 0;
        int maxDay = daysLeft[0];

        for (int i = 0; i < n; i++) {
            if (daysLeft[i] <= maxDay) { //배포 가능일이 가장 늦은 배포일보다 빠르면
                count++;
            } else {
                //배포 예정일이 기준 배포일보다 늦어지면
                answer.add(count);
                count = 1;
                maxDay = daysLeft[i];
            }
        }
        answer.add(count);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
