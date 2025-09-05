import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        // dp[i][c]: 0에서 i번째 집까지 칠했을 때 i번째 집을 c로 칠하는 최소 비용
        
        int n = sc.nextInt();

        int[][] cost = new int[n][3];
        for (int i = 0; i < n; i++) {
            cost[i][0] = sc.nextInt(); // R
            cost[i][1] = sc.nextInt(); // G
            cost[i][2] = sc.nextInt(); // B
        }

        int[][] dp = new int[n][3];
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];
        

        // 점화식을 그대로 구현
        for (int i = 1; i < n; i++) {
            dp[i][0] = cost[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = cost[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = cost[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        int ans = Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
        System.out.println(ans);
    }
}
