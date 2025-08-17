import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 이중 우선순위 큐
        // 데이터를 삽입하는 연산과 삭제하는 연산
        // 삭제하는 연산은 가장 높은 것, 가장 낮은 것 삭제 2가지로 구분

        int T = sc.nextInt(); // 입력 데이터의 수
        // I 는 추가하는 연산 (동일 정수 삽입 가능), D는 삭제하는 것 -가 붙으면 최솟값 삭제

        // 2개의 테스크가 존재.
        for(int i = 0; i < T; i++) {
            int k = sc.nextInt(); // 연산의 개수
            PriorityQueue<Node> minPQ = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));
            PriorityQueue<Node> maxPQ = new PriorityQueue<>((x, y) -> Integer.compare(y.value, x.value));

            boolean[] alive = new boolean[k + 5];
            int nextId = 0;
            
            for(int j = 0; j < k; j++) {

                String op = sc.next();
                int x = sc.nextInt();

                if(op.equals("I")) {
                    alive[nextId] = true;
                    Node node = new Node(nextId, x);
                    minPQ.offer(node);
                    maxPQ.offer(node);
                    nextId++;
                } else {
                    // Delete
                    if(x == 1) {
                        clean(maxPQ, alive);
                        if (!maxPQ.isEmpty()) {
                            Node top = maxPQ.poll();
                            // 가장 큰 거에서 뺀 것에 false처리해서 없애줌.
                            alive[top.id] = false;
                        }
                    } else if (x == -1) {
                        clean(minPQ, alive);
                        if (!minPQ.isEmpty()) {
                            Node top = minPQ.poll();
                            alive[top.id] = false;
                        }
                    }
                }
            }

            clean(minPQ, alive);
            clean(maxPQ, alive);

            if (minPQ.isEmpty() || maxPQ.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(maxPQ.peek().value + " " + minPQ.peek().value);
            }
        }
    }

    static void clean(PriorityQueue<Node> pq, boolean[] alive) {
        while (!pq.isEmpty() && !alive[pq.peek().id]){
            pq.poll();
        }
    }

    public static class Node {
        int id;
        int value;

        public Node(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }
}
