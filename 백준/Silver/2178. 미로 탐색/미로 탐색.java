import java.util.*;

public class Main {
    public static final int[] dx = new int[]{-1, 1, 0, 0};
    public static final int[] dy = new int[]{0, 0, -1, 1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] maze = new int[n][m];
        for(int i = 0; i < n; i++) {
            String str = sc.next();

            for(int j = 0; j < m; j++) {
                maze[i][j] = Integer.parseInt(str.charAt(j) + "");
            }
        }

        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(0, 0));
        
        int[][] distance = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], -1);
        }
        distance[0][0] = 1;

        // BFS 순회
        while(!queue.isEmpty()) {
            Point cur = queue.poll();

            if(cur.x == n - 1 && cur.y == m - 1) {
                System.out.println(distance[cur.x][cur.y]);
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m) {
                    if(distance[nextX][nextY] == -1 && maze[nextX][nextY] == 1) {
                        queue.offer(new Point(nextX, nextY));
                        distance[nextX][nextY] = distance[cur.x][cur.y] + 1;
                    }
                }
            }
        }
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}