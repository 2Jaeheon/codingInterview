import java.util.*;

public class Main {

    public static final int[] dx = new int[]{-1, 1, 0, 0};
    public static final int[] dy = new int[]{0, 0, -1, 1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int startX = 0;
        int startY = 0;

        int[][] graph = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                graph[i][j] = sc.nextInt();

                if(graph[i][j] == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }

        int[][] distance = new int[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(distance[i], -1);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY});
        distance[startX][startY] = 0;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nextX = cur[0] + dx[i];
                int nextY = cur[1] + dy[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m) {
                    if (graph[nextX][nextY] == 1 && distance[nextX][nextY] == -1) {
                        queue.offer(new int[]{nextX, nextY});
                        distance[nextX][nextY] = distance[cur[0]][cur[1]] + 1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(distance[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}