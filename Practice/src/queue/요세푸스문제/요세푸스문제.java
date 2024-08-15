package queue.요세푸스문제;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 요세푸스문제 {

    public static void main(String[] args) {
        int N = 5;
        int k = 2;

        System.out.println(solution(N, k));
    }

    public static int solution(int n, int k) {
        int answer = 0;
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        //k번 반복해서 앞에서 빼서 뒤로 넣고 하나를 없앰, 이 과정을 1개보다 클 때까지 반복
        while (queue.size() > 1) {
            for (int j = 1; j < k; j++) {
                queue.add(queue.poll());
            }
            queue.poll();
        }

        //큐에 남아있는 마지막 원소 하나를 반환
        return queue.peek();
    }

    public static int solution2(int n, int k) {
        List<Integer> list = new ArrayList<>();

        //리스트에 1부터 n까지의 값 초기화
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        //원소의 값은 1부터 시작하지만 인덱스값은 0부터 시작이기 때문에 index = 0
        int index = 0;
        int answer = 0;
        while (list.size() > 1) {
            index = (index + k - 1) % list.size();
            answer = list.get(index);
            list.remove(index);
        }

        return list.getFirst();
    }
}
