import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 최대힙
        // 배열에서 가장 큰 값을 출력하고 이를 배열에서 제거

        int n = sc.nextInt();
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        
        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();

            if(x == 0) {
                if(heap.size() == 0) {
                    System.out.println(0); 
                } else {
                    System.out.println(heap.poll());
                }
            } else {
                heap.offer(x);
            }
        }
    }
}