import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 가중치 없는 방향그래프 G
        // i, j에 대해서 i에서 j로 가는 길이가 양수인 경로가 있는지 없는지 구함.

        // 첫번째 줄의 두번째 숫자가 1인 경우는 1에서 2로 가는 간선이 존재한다는 뜻
        // 0인 경우는 없다는 뜻

        int N = sc.nextInt();
        int[][] graph = new int[N][N];
        int[][] reachable = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < N; i++) {
            bfs(graph, reachable, i, N);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(reachable[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void bfs(int[][] graph, int[][] reachable, int start, int N) {
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int next = 0; next < N; next++) {
                if(graph[cur][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        for(int i = 0; i < N; i++) {
            if(visited[i]) {
                reachable[start][i] = 1;
            }
        }
    }
}