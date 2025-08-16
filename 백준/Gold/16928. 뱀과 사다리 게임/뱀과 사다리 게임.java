import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 주사위를 조작해서 최소 몇 번만에 도착점에 도달할까?

        // 10 x 10 size
        // 주사위는 1 ~ 6
        // 주사위를 나온 수만큼 이동
        // 100번째 칸에 도달하는 것이 목표임.
        // 사다리를 타면 올라가고, 뱀을 만나면 내려간다.

        int n = sc.nextInt();
        int m = sc.nextInt();
        Map<Integer, Integer> map = new HashMap<>();

        // 사다리
        for(int i = 0; i < n + m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            map.put(from, to);
        }

        int[] graph = new int[101];

        // BFS로 풀 수 있지 않을까??
        // 1~6을 계속해서 더하면서 사다리가 있으면 올라가고, 뱀이 있으면 내려가면 되지 않을까?
        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[101];
        queue.offer(new Node(1, 0));
        visited[1] = true;

        while(!queue.isEmpty()) {
            Node cur = queue.poll();

            for(int i = 1; i <= 6; i++) {
                int next = cur.position + i;

                if (map.containsKey(next)) {
                    next = map.get(next);
                }

                if(next > 100) {
                    continue;
                }

                if(next == 100) {
                    System.out.println(cur.count + 1);
                    return ;
                }

                if(!visited[next]) {
                    queue.offer(new Node(next, cur.count + 1));
                    visited[next] = true;
                }
            }
        }
    }

    public static class Node{
        int position;
        int count;

        public Node(int position, int count) {
            this.position = position;
            this.count = count;
        }
    }
}
