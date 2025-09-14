import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 수빈이는 점 N
        // 동생은 점 K
        // 수인이의 위치가 X일때 걸으면 X-1 or X+1 로 이동
        // 수간이동하면 2 * X

        // 어? 이거 페이지랭크..?
        // 데이터마이닝때 배운 거 아닌가?
        int N = sc.nextInt();
        int K = sc.nextInt();

        // i 에서 i + 1까지 갈 수 있는 경우의 수
        // 1. i 에서 +1 => 1s
        // 2. i 에서 -1 => 1s
        // 3. i 에서 *2 => 0s
        if (N >= K) {
            System.out.println(N - K);
            return;
        }

        // 전형적인 DP 문제인 거 같음
        // memoization + DFS도 풀어볼 수 있을듯
        // dp로 풀 수 있는게 맞아? i-1도 있어서 안 되지 않을까? 
        // 인될 거 같은데 왜냐면 미래의 값과 과거의 값이 공존하니까 불가능할 거 같은데
        // 그러면 그래프 이론으로 풀 수 있지 않을까
        // BFS로 풀어보자

        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.time));
        boolean[] visited = new boolean[100_001];
        queue.offer(new Node(N, 0));

        int forward, backward, teleport = 0;
        
        while(!queue.isEmpty()) {
            Node cur = queue.poll();

            // 현재 위치를 방문했다면 패스
            if (visited[cur.value]) {
                continue;
            }

            visited[cur.value] = true; // 방문 처리

            // 목표에 도달했다면 종료
            if (cur.value == K) {
                System.out.println(cur.time);
                return;
            }

            // 앞으로 걷기
            forward = cur.value + 1;
            if(forward <= 100000 && !visited[forward]) {
                queue.offer(new Node(forward, cur.time + 1));
            }

            // 뒤로 걷기
            backward = cur.value - 1;
            if(backward >= 0 && !visited[backward]) {
                queue.offer(new Node(backward, cur.time + 1));
            }

            // 순간 이동
            teleport = cur.value * 2;
            if(teleport <= 100000 && !visited[teleport]) {
                queue.offer(new Node(teleport, cur.time));
            }
            
        }
    }

    static class Node {
        int value;
        int time;

        public Node(int value, int time) {
            this.value = value;
            this.time = time;
        }
    }
}
