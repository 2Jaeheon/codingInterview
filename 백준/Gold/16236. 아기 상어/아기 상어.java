import java.util.*;

public class Main{

    static class BabyShark {
        int x;
        int y;
        int size;
        int eatCount;

        public BabyShark(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = 2;
            this.eatCount = 0;
        }
    }

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 물고기 M, 아기상어 1
        // 초기 아기상어 크기 2
        // 자신의 크기와 같은 수의 물고기를 먹을 때 크기 1 증가
        // 1초마다 인접 칸으로 이동
        // 자기보다 큰 물고기가 있는 칸으로 이동 불가

        // 자기보다 작은 물고기를 DFS를 돌려 찾는다.
        // 위를 반복한다.
        
        // init
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        BabyShark shark = null;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();

                if(map[i][j] == 9) {
                    shark = new BabyShark(i, j);
                    map[i][j] = 0;
                }
            }
        }

        int time = 0;

        while (true) {
            // 현재 상어 위치에서 BFS로 거리 계산
            // dist 배열을 채움
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], -1);
            }

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{shark.x, shark.y});
            dist[shark.x][shark.y] = 0;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];

                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    // 범위 체크
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    // 이미 방문했는지
                    if (dist[nx][ny] != -1) continue;
                    // 지나갈 수 있는 칸인지
                    if (map[nx][ny] > shark.size) continue;

                    dist[nx][ny] = dist[x][y] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }

            int targetX = -1;
            int targetY = -1;
            int minDist = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][j] == -1) continue; // 도달 불가
                    if (map[i][j] == 0) continue;   // 물고기 없음
                    if (map[i][j] >= shark.size) continue; // 먹을 수 없음 (같거나 큼)

                    // 여기까지 왔으면 먹을 수 있는 물고기
                    if (dist[i][j] < minDist) {
                        minDist = dist[i][j];
                        targetX = i;
                        targetY = j;
                    }
                }
            }

            // 먹을 수 있는 물고기를 못 찾으면 종료
            if (targetX == -1 || targetY == -1) {
                System.out.println(time);
                return;
            }

            // 그 물고기까지 이동하고 먹기
            time += minDist;
            shark.x = targetX;
            shark.y = targetY;
            map[targetX][targetY] = 0; // 물고기 먹어서 없앰

            shark.eatCount++;
            if (shark.eatCount == shark.size) {
                shark.size++;
                shark.eatCount = 0;
            }
        }
    }
}
