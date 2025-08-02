import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 배열에 정수 x를 넣는다.
        // 배열에서 절댓값이 가장 작은 값을 출력하고 값을 배열에서 제거
        // 여러개라면 가장 작은 수를 출력하고 값을 배열에서 제거

        int n = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int absCompare = Integer.compare(Math.abs(a), Math.abs(b));
            if (absCompare == 0) {
                return Integer.compare(a, b); // 절댓값 같으면 실제 값 비교
            }
            return absCompare;
        });

        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();

            if(x == 0) {
                if (pq.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(pq.poll());
                }
            } else {
                pq.offer(x);
            }

        }
    }
}