import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // LCS => 두 수열이 주어질 때 모두의 부분수열 중 가장 긴 것
        // Longest Common Subsequence
        // ACAYKP
        // CAPCAK
        // Answer: ACAK

        // 어떻게 풀어야할까?
        // 연속해서 일치할 필요가 없음
        // 순차적으로 인덱스를 하나씩 증가하면서 그 뒤에 있는지를 체크하면서
        // 인덱스 뒤에 있는지를 체크해서 추가해주면 되지 않을까??
        // 근데 그게 최적갑을 보장해주는 것은 아님
        // 그러면 어떻게 풀어야하지??

        // 다음의 방법으로 풀 수 있을까?
        // 1. 투포인터
        // 2. DP(?)

        // DP를 통해서 풀 수 있지않을까??
        // DP를 통해서 풀려면 어떻게 풀어야할까??
        // 뒤에서부터 구한다고 생각을 할 때 앞에 있는 것들이 다 LCS길이를 알고있다면 마지막 문자만 비교하면 됨.
        // 이제 맨 뒤의 원소 두 개가 같을 때와 다를 때로 나누어서 생각해볼 수 있음.
        // 그렇다면 뒤에서부터 순차적으로 생각해보았을 때 앞의 LCS를 안다면 풀 수 있는거고
        // 그렇게 했을 때 맨 앞에서부터 LCS길이를 저장하는 방식으로 해나가면 되겠네

        String a = sc.next();
        String b = sc.next();
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        
        // dp[i][j] == i번째 글자까지와 j번쨰 글자까지의 LCS값
        // 만일 맨 뒤에가 같다면?
        // ex) ACAYKK & CAPCAK => dp[i][j]에다가 1 더하면 될 것 같음
        // 만일 맨 뒤에가 다르다면?
        // ex) ACAYKP & CAPCAK => MAX값을 구하면 됨

        for(int i = 1; i <= a.length(); i++) {
            for(int j = 1; j <= b.length(); j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }


        
        System.out.println(dp[a.length()][b.length()]);
    }
}
