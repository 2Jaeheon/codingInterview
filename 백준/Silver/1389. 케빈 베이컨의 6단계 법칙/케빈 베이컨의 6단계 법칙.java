import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1 - 3
        // 1 - 4
        // 2 - 3
        // 3 - 4
        // 4 - 5

        // 1은 2까지 2단계, 3까지 1단계, 4까지 1단계 5까지 2단계 => 2 + 1 + 1 + 5
        // 이거 나를 제외한 모든 노드까지의 최소거리아냐?
        // 그러면 BFS를 쓰면 되겠다.
        // 모든 노드에서 케빈 베이컨 수를 구한 뒤
        // 최소인 사람을 구하면 됨.
        
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<ArrayList<Integer>> graph = new ArrayList<>();
 
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            // 양방향으로 그래프 세팅 
            graph.get(from).add(to);
            graph.get(to).add(from);
        }


        int min = Integer.MAX_VALUE;
        int answer = -1;

        // 각 사람별 케빈베이컨 수 계산
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            int[] dist = bfs(graph, n, i);

            // 자기 자신 제외하고 거리 합산
            for (int j = 1; j <= n; j++) {
                sum += dist[j];
            }

            // 최소값 갱신
            if (sum < min) {
                min = sum;
                answer = i;
            }
        }

        System.out.println(answer);
    }

    public static int[] bfs(List<ArrayList<Integer>> graph, int n, int start) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1); // 아직 방문 안 한 노드는 -1
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        distance[start] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (distance[next] == -1) {
                    distance[next] = distance[cur] + 1;
                    queue.offer(next);
                }
            }
        }
        return distance;
    }
}