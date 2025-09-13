import java.util.*;

public class Main{
    
    static int N;
    static int K;
    static int[][] dp;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        dp = new int[N + 1][K + 1];

        for(int i = 1; i <= N; i++) { // i: 물건 인덱스
            int weight = sc.nextInt();
            int value = sc.nextInt();

            for(int j = 1; j <= K; j++) { // j: 배낭의 무게 한도
                if(weight > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int value_not_taken = dp[i - 1][j];
                    int value_taken = value + dp[i - 1][j - weight];
                    dp[i][j] = Math.max(value_not_taken, value_taken);
                }
            }
        }
        
        System.out.println(dp[N][K]);
    }
}
