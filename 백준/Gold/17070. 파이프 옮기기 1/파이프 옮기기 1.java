import java.util.*;

public class Main{

    static int N;
    static int answer = 0;
    static int[][] arr;
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        // DP로 풀어보도록 하자.
        // DP[r][c][direction]: 파이프의 오른쪽 아래 끝부분이 (r, c) 좌표에 direction 방향으로 도착하는 방법의 수
        long[][][] dp = new long[N][N][3];
        // dp[r][c][0] 이 되기 위해서는 이전에 가로 또는 대각선 이었어야 함.
        
        dp[0][1][0] = 1;

        for(int r = 0; r < N; r++) {
            for(int c = 2; c < N; c++) {
                if(arr[r][c] == 1) {
                    continue;
                }

                // r, c에 가로로 도착하는 경우
                dp[r][c][0] = dp[r][c-1][0] + dp[r][c-1][2];
                // r, c에 세로로 도착하는 경우
                
                if (r > 0) {
                    // 이전 칸 (r-1, c)이 세로 또는 대각선이었어야 함
                    dp[r][c][1] = dp[r-1][c][1] + dp[r-1][c][2];
                }

                if (r > 0 && arr[r-1][c] == 0 && arr[r][c-1] == 0) {
                    // 이전 칸 (r-1, c-1)이 가로, 세로, 대각선 모두 가능
                    dp[r][c][2] = dp[r-1][c-1][0] + dp[r-1][c-1][1] + dp[r-1][c-1][2];
                }
            }
        }
        
        long answer = dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2];
        System.out.println(answer);
    }
}
