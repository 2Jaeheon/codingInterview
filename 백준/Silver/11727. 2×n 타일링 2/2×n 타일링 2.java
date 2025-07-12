import java.util.*;

public class Main {
    public static void main(String[] args) {
        int MOD = 10007;
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] dp = new int[n + 1];

        dp[0] = 1;
        if (n >= 1) {
            dp[1] = 1;
        }

        // 마지막 두 개만 확인해보면 됨. 그 전까지는 모두 반영되는 것.
        // 점화식을 계산해보면 => dp[n] = dp[n-1] + 2 * dp[n-2]
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % MOD;
        }

        System.out.println(dp[n]);
    }
}