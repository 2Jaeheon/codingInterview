import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] dp = new int[n][n];
        int[][] arr = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        dp[0][0] = arr[0][0];

        for (int i = 1; i < n; i++) {
            // 가장 왼쪽과 오른쪽
            dp[i][0] = dp[i-1][0] + arr[i][0];
            dp[i][i] = dp[i-1][i-1] + arr[i][i];

            // 가운데들
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j];
            }
        }

        int ans = 0;
        for (int j = 0; j < n; j++) {
            ans = Math.max(ans, dp[n-1][j]);
        }

        System.out.println(ans);
    }
}
