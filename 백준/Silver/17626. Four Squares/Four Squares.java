import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 모든 자연수는 넷 혹은 그 이하의 제곱수의 합으로 표현할 수 있다.
        // n이 주어질 때 최소 개수의 제곱수 합으로 표현하라
        int n = sc.nextInt();
        int k = (int)Math.sqrt(n);
        int[] arr = new int[k];
        for (int i = 1; i <= k; i++) {
            arr[i - 1] = i * i;
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        // i는 하나씩 증가하면서 n이 될 때까지 dp[n]을 계속해서 갱신 
        for(int i = 1; i <= n; i++) {
            // j는 제곱수들을 저장한 배열을 탐색하기 위한 인덱스
            for(int j = 0; j < arr.length; j++) {
                // i - arr[j]가 음수면 런타임 에러가 발생
                // 유효한 경우에만 계산
                if (i - arr[j] >= 0) {
                    // i == 26
                    // j => 0, 1, 2, 3, 4
                    // arr[j] => 1, 4, 9, 16, 26
                    // dp[26] => dp[26-1] + 1, dp[26-4] + 1, dp[26-9] + 1, dp[26-25] + 1
                    dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
                }
            }
        }

        // 12 -> 1, 4, 9로 가능함.
        // dp[12] = dp[12 - 1], dp[12 - 4], dp[12 - 9]중 가장 작은 것 선택
        System.out.println(dp[n]);
    }
}