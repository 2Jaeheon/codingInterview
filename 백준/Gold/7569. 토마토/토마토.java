import java.util.*;

public class Main{
    public static final int[] dx = new int[]{1, -1, 0, 0, 0, 0};
    public static final int[] dy = new int[]{0, 0, 1, -1, 0, 0};
    public static final int[] dz = new int[]{0, 0, 0, 0, 1, -1};
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt(); // 가로
        int n = sc.nextInt(); // 세로
        int h = sc.nextInt(); // 높이

        // 입력: m * n이 h개 주어짐
        // 토마토가 모두 익는데 얼마나 걸리는지 계산해야함. 
        // 처음부터 다 익어있으면 0
        // 모두 익지 못하면 -1

        // BFS를 돌리기 위한 작업
        Queue<int[]> queue = new ArrayDeque<>();

        int[][][] box = new int[h][n][m];
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < m; k++) {
                    box[i][j][k] = sc.nextInt();
                    if(box[i][j][k] == 1) {
                        queue.offer(new int[]{i, j, k});
                    }
                }
            }
        }

        // BFS 수행
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curZ = cur[0];
            int curY = cur[1];
            int curX = cur[2];

            for(int i = 0; i < 6; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                int nextZ = curZ + dz[i];

                if (nextX >= 0 && nextX < m &&
                    nextY >= 0 && nextY < n &&
                    nextZ >= 0 && nextZ < h) {
                    if(box[nextZ][nextY][nextX] == 0) {
                        box[nextZ][nextY][nextX] = box[curZ][curY][curX] + 1;
                        queue.offer(new int[]{nextZ, nextY, nextX});
                    }
                }
            }
        }

        // 마지막 순회
        int maximum = -100000;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < m; k++) {
                    if(box[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }

                    maximum = Math.max(maximum, box[i][j][k]);
                }
            }
        }

        System.out.println(maximum - 1);
    }
}
