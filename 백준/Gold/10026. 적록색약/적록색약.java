import java.util.*;

public class Main{

    public static final int[] dx = new int[]{-1, 1, 0, 0};
    public static final int[] dy = new int[]{0, 0, -1, 1};
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 확실한 그래프 이론
        // 영역을 나눠야 함.
        // 적녹색맹일 때와 아닐 때로 나눠서 처리해야함.

        // 그래프를 두 개를 두고 할까 아니면 하나를 재활용할까
        // 두 개를 사용하는게 좋지 않을까?
        // 일단 하나를 사용해서 사용
        char[][] graph = new char[n][n];
        for (int i = 0; i < n; i++) {
            String input = sc.next();
            for (int j = 0; j < n; j++) {
                graph[i][j] = input.charAt(j);
            }
        }

        boolean[][] visited = new boolean[n][n];
        int normal = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    // 방문하지 않은 정상 경우에 관해서 BFS
                    bfs(graph, visited, i, j);
                    normal++;
                }
            }
        }
        
        char[][] cb = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 적녹색맹의 경우 G → R
                if(graph[i][j] == 'G') {
                    graph[i][j] = 'R';
                }

                cb[i][j] = graph[i][j];
            }
        }

        boolean[][] visited2 = new boolean[n][n];
        int colorBlind = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited2[i][j]) {
                    bfs(cb, visited2, i, j);
                    colorBlind++;
                }
            }
        }

        System.out.println(normal + " " + colorBlind);
    }


    static void bfs(char[][] grid, boolean[][] visited, int sx, int sy) {
        int n = grid.length;
        char color = grid[sx][sy];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) 
                    continue;
                if (visited[nx][ny]) 
                    continue;
                if (grid[nx][ny] != color) 
                    continue;

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }
}
