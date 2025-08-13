import java.util.*;

public class Main{
    public static final int[] dx = new int[]{0, 0, -1, 1};
    public static final int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
    
        int m = sc.nextInt();
        int n = sc.nextInt();

        Queue<int[]> queue = new ArrayDeque<>();
        
        int[][] box = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                box[i][j] = sc.nextInt();

                if (box[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nextX = cur[0] + dx[i];
                int nextY = cur[1] + dy[i];

                if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < m) {
                    if(box[nextX][nextY] == 0) {
                        queue.offer(new int[]{nextX, nextY});
                        box[nextX][nextY] = box[cur[0]][cur[1]] + 1;
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(box[i][j] == 0) {
                    System.out.println(-1);
                    return ;
                }
                max = Math.max(max, box[i][j]);
            }
        }
        
        if(max == 1) {
            System.out.println(0);
        } else {
            System.out.println(max - 1);
        }
    }
}
