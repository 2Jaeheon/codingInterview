import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 수빈이는 점 N에 있고 동생은 K에 존재
        // 걷거나 순간이동 가능
        // 수빈이의 위치가 N일 때 걸으면 X - 1 or X + 1로 이동 가능
        // 순간이동시 2 * X로 이동

        // 수빈이가 동생을 찾을 수 있는 가장 빠른 시간

        // 아마 최단거리니까 Graph와 BFS를 생각할 수 있음
        // 갈 수 있는 선택지는 x-1 / x+1 / 2 * x
        // 각 위치에서 세 개의 선택지를 추가해서 그래프를 만들고 그래프를 BFS를 해서 
        // 몇 번 갔는지를 체크하면 됨.

        int n = sc.nextInt();
        int k = sc.nextInt();
        final int MAX = 300000;
        
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[MAX + 1];
        
        visited[n] = true;
        queue.offer(n);
        int[] dist = new int[MAX + 1];

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == k) {
                System.out.println(dist[cur]);
                break;
            }

            int[] nexts = {cur - 1, cur + 1, cur * 2};
            // 세 개를 반복
            for (int next : nexts) {
                // 범위 안에 있고 아직 방문하지 않았다면
                if (0 <= next && next <= MAX && !visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[cur] + 1; // 현재까지 시간 + 1초
                    queue.offer(next);
                }
            }
        }

    }
}