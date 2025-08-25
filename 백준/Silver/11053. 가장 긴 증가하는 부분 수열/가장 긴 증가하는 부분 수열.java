import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int[] arr = new int[a];

        for(int i = 0; i < a; i++) {
            arr[i] = sc.nextInt();
        }

        // dp[i]: 인덱스 i에서 끝나는 부분 증가수열의 최장 길이
        // 기본값은 1
        int[] dp = new int[a];
        int answer = 0;
        
        for (int i = 0; i < a; i++) {
            dp[i] = 1; // 자기 자신만 선택하는 기본값
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        
        System.out.println(answer);
    }
}
