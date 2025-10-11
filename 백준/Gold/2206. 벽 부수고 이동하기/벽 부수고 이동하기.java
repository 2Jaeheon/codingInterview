import java.util.*;

public class Main{
    static int dx[] = new int[]{-1, 1, 0, 0};
    static int dy[] = new int[]{0, 0, -1, 1};
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 1 -> 벽
        // 0 -> 이동 가능
        // 1,1 에서 N, M까지 이동해야하는데 최단거리로 이동해야함.
        // 최단 경로는 맵에서 가장 적은 개수의 칸을 지나는 경로
        // 시작하는 칸과 끝나는 칸도 포함해서 셈

        // 만약 한 개의 벽을 부수고 이동하는 것이 경로가 더 짧다면, 벽을 한 개 까지 부숴도 됨.
        // 이동 가능한 칸은 상하좌우

        // 데이터 어떻게 저장? -> 당연히 2차원배열
        // 2차원 배열에 데이터를 세팅하자.
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++) {
            String input = sc.next();
            
            for(int j = 0; j < input.length(); j++) {
                char c = input.charAt(j);
                map[i][j] = c - '0';
            }
        }


        // 이제 로직을 고민해보자
        // (1, 1) -> (N, M)으로 가야함.
        // 벽을 한 번 뚫을 수 있음
        // BFS로 해야하지 않을까??
        // 인접한 곳중에 갈 수 있는 곳을 보고 게속해서 가는 방향으로 진행
        // 근데 벽은 어떻게 처리하지...??
        // 각 좌표에서 벽을 뚫어보면 되지 않을까??
        // 각 좌표마다 0으로 바꿔서 처리하면 가능할 거 같은데?
        // 그러면 벽의 상태를 어떻게 관리하면 좋을까?
        // 3차원배열..??
        // 아니면 새로운 배열??
        // 3차원 배열로 처리가 가능할 거 같음 
        // 각 좌표마다 0/1로 나눠서 방문 했는지 안 했는지를 알 수 있지 않을까?
        // 일단 시작해보자.

        
        // dist[y][x][k]
        // k = 0: 벽을 한 번도 안 부숨
        // k = 1: 벽을 한 번 부숨
        // 값 = 거리
        int dist[][][] = new int[n][m][2];
        Queue<int[]> queue = new ArrayDeque<>();
        dist[0][0][0] = 1;
        queue.offer(new int[]{0, 0, 0});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int k = cur[2];
            
            // 도착지 도달 시 거리 출력 후 종료
            if(y == n-1 && x == m-1) {
                System.out.println(dist[y][x][k]);
                return;
            }

            // 4방위 탐색
            for(int dir = 0; dir < 4; dir++) {
                int nextX = x + dx[dir];
                int nextY = y + dy[dir];

                // 맵 범위 벗어남
                if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                    continue;
                }

                // 다음 칸이 빈 칸일 때
                if(map[nextY][nextX] == 0) {
                    int nextK = k;
                    // 아직 방문하지 않았다면 거리를 갱신하고 큐에 추가
                    if(dist[nextY][nextX][nextK] == 0) {
                        dist[nextY][nextX][nextK] = dist[y][x][k] + 1;
                        queue.offer(new int[]{nextY, nextX, nextK});
                    }
                } else { // 다음 칸이 벽인데, 벽을 부수지 않았다면 벽을 부수고 이동
                    if(k == 0 && dist[nextY][nextX][1] == 0) {
                        dist[nextY][nextX][1] = dist[y][x][k] + 1;
                        queue.offer(new int[]{nextY, nextX, 1});
                    }
                }
            }
        }
        
        System.out.println(-1);
    }
}
