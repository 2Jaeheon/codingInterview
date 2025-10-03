import java.util.*;

public class Main{

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static int r;
    static int c;
    static int t;
    static int[][] room;

    static class Dust {
        int x;
        int y;
        int value;

        public Dust(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();
        t = sc.nextInt();
        room = new int[r][c];
        List<Dust> dustCells = new ArrayList<>();
        int airPurifierTopRow = -1;
        
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                room[i][j] = sc.nextInt();

                if (room[i][j] == -1 && airPurifierTopRow == -1) {
                    airPurifierTopRow = i;
                }
            }
        }

        // System.out.println(Arrays.deepToString(room));


        // for(Dust dust : dustCells) {
        //     System.out.print(dust.x + " ");
        //     System.out.print(dust.y + " ");
        //     System.out.println(dust.value);
        // }
        
        for(int time = 0; time < t; time++) {

            dustCells.clear();
            for(int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if(room[i][j] > 0) {
                        dustCells.add(new Dust(i, j, room[i][j]));
                    }
                }
            }
            
            int[][] tempRoom = new int[r][c];

            // 미세먼지 확산
            for(Dust dust : dustCells) {
                int curX = dust.x;
                int curY = dust.y;
                int curValue = room[curX][curY];
                int spreadAmount = curValue / 5;
                int count = 0;
                
                for(int j = 0; j < 4; j++) {
                    int nextX = dust.x + dx[j];
                    int nextY = dust.y + dy[j];
                    
                    if(nextX >= 0 && nextX < r && nextY >= 0 && 
                       nextY < c && room[nextX][nextY] != -1) {
                        
                        // (수정) room이 아닌 tempRoom에 확산된 양을 더합니다.
                        tempRoom[nextX][nextY] += spreadAmount;
                        count++;
                    }
                }

                tempRoom[curX][curY] += curValue - (spreadAmount * count);
            }

            for(int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if(room[i][j] != -1) {
                        room[i][j] = tempRoom[i][j];
                    }
                }
            }

            // 공기청정기 로직
            int top = airPurifierTopRow;
            int bottom = airPurifierTopRow + 1;
            
            // 위쪽 공기청정기 (반시계방향 순환)
            // 아래 -> 위 (왼쪽 세로줄)
            for (int i = top - 1; i > 0; i--) {
                room[i][0] = room[i - 1][0];
            }
            // 오른쪽 -> 왼쪽 (맨 위 가로줄)
            for (int j = 0; j < c - 1; j++) {
                room[0][j] = room[0][j + 1];
            }
            // 위 -> 아래 (오른쪽 세로줄)
            for (int i = 0; i < top; i++) {
                room[i][c - 1] = room[i + 1][c - 1];
            }
            // 왼쪽 -> 오른쪽 (공기청정기 가로줄)
            for (int j = c - 1; j > 1; j--) {
                room[top][j] = room[top][j - 1];
            }
            // 공기청정기에서 나오는 바람은 깨끗함
            room[top][1] = 0;
            
            
            // 아래쪽 공기청정기 (시계방향 순환)
            // 위 -> 아래 (왼쪽 세로줄)
            for (int i = bottom + 1; i < r - 1; i++) {
                room[i][0] = room[i + 1][0];
            }
            // 오른쪽 -> 왼쪽 (맨 아래 가로줄)
            for (int j = 0; j < c - 1; j++) {
                room[r - 1][j] = room[r - 1][j + 1];
            }
            // 아래 -> 위 (오른쪽 세로줄)
            for (int i = r - 1; i > bottom; i--) {
                room[i][c - 1] = room[i - 1][c - 1];
            }
            // 왼쪽 -> 오른쪽 (공기청정기 가로줄)
            for (int j = c - 1; j > 1; j--) {
                room[bottom][j] = room[bottom][j - 1];
            }
            // 공기청정기에서 나오는 바람은 깨끗함
            room[bottom][1] = 0;
            
        }
        
        int totalDust = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (room[i][j] > 0) {
                    totalDust += room[i][j];
                }
            }
        }
        
        System.out.println(totalDust);
    }
}
