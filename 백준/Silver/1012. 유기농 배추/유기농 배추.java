import java.util.*;

public class Main {

    public static int[] dx = new int[]{0, 0, -1, 1};
    public static int[] dy = new int[]{1, -1, 0, 0};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        
        for(int i = 0; i < T; i++) {
            int count = 0;
            int m = sc.nextInt();
            int n = sc.nextInt();
            int k = sc.nextInt();

            int[][] arr = new int[m][n];

            for(int j = 0; j < k; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();

                arr[x][y] = 1;
            }
            boolean[][] visited = new boolean[m][n];
            
            // 모든 배열을 시작점으로 해서 bfs 순회를 하자.
            for(int a = 0; a < m; a++) {
                for(int b = 0; b < n; b++) {
                    // arr[a][b] 가 1이고 방문하지 않았을 때만 bfs 순회
                    if(arr[a][b] == 1 && !visited[a][b]) {
                        // a, b를 기준으로 bfs 순회를 함.
                        bfs(a, b, arr, visited, m, n);
                        // 그리고 count를 증가시킴.
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    // bfs 함수
    static void bfs(int a, int b, int[][] arr, boolean[][] visited, int m, int n) {
        
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(a, b));
        visited[a][b] = true;

        // 큐를 순회
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // 상 하 좌 우로 반복해서 인접한 것을 순회
            for (int dir = 0; dir < 4; dir++) {
                // next X, Y를 구해서 사용
                int nx = current.x + dx[dir];
                int ny = current.y + dy[dir];
                // 배열의 크기를 벗어나지 않도록 함.
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    // 만약 다음에 가는 x, y가 1이고, 방문을 하지 않았다면
                    if (arr[nx][ny] == 1 && !visited[nx][ny]) {
                        // 방문처리를 하고 queue를 추가
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
        // 이 과정을 끝내면 1을 기준으로 인근 상하좌우가 모두 방문처리가 됨.
    }

    static class Point {
        int x;
        int y;

        public Point() {}
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}