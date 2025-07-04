import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] scores = new int[n];
        int[] dp = new int[n];
        
        // 10 20 15 25 10 20
        for(int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }

        dp[0] = scores[0];
        if(n > 1) {
            dp[1] = scores[0] + scores[1];            
        }
        if(n > 2) {
            dp[2] = Math.max(scores[0] + scores[2], scores[1] + scores[2]);
        }
        
        for(int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + scores[i], dp[i - 3] + scores[i - 1] + scores[i]);
        }

        System.out.println(dp[n - 1]);
    }
}