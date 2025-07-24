import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int n = sc.nextInt();

        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();

            if(x == 0) {
                if (heap.isEmpty()) {
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