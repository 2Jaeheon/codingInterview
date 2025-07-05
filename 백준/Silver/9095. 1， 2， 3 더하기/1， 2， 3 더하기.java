import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // n == 1 -> 1
        // n == 2 -> 1 + 1, 2 == 2
        // n == 3 -> 1 + 1 + 1, 2 + 1, 1 + 2, 3 == 4
        // n == 4 -> 1 + 1 + 1 + 1, 1 + 1 + 2, 1 + 2 + 1, 2 + 1 + 1, 2 + 2, 1 + 3, 3 + 1 == 7

        // f(n) = f(n-1) + f(n-2) + f(n-3)

        int n = sc.nextInt();
        
        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int[] dp = new int[x + 1];
            dp[1] = 1;
            if(x >=2 ) {
                dp[2] = 2;    
            }
            if(x >= 3) {
                dp[3] = 4;    
            }
            if(x >= 4) {
                for(int j = 4; j <= x; j++) {
                    dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
                }
            }
            System.out.println(dp[x]);
        }
    }
}