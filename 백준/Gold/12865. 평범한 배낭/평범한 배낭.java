import java.util.*;

public class Main{
    
    static int N;
    static int K;
    static int[] weights;
    static int[] values;
    static int[][] dp;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 여행에 필요하다고 생각하는 N개의 물건
        // 각 물건은 Weight와 Value를 가짐
        // 해당 물건을 가지면 V만큼 즐길 수 있음
        // K만큼의 무게만을 넣을 수 있음

        // 가방문제!
        N = sc.nextInt();
        K = sc.nextInt();
        weights = new int[N];
        values = new int[N];
        dp = new int[N][K + 1];
        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        // 어떻게 풀어야할까?
        // 전형적인 DP류의 문제로 보임
        // 메모이제이션 + DFS로 풀 수 있을 것 같음
        
        // N개의 물건들을 순회
        for(int i = 0; i < N; i++) {
            int w = sc.nextInt();
            int v = sc.nextInt();

            weights[i] = w;
            values[i] = v;
        }

        System.out.println(dfs(0, K));
    }

    public static int dfs(int idx, int leftWeight) {
        if (idx == N) {
            return 0;
        }

        // memoization
        if (dp[idx][leftWeight] != -1) {
            return dp[idx][leftWeight];
        }

        // 값을 취하지 않았을 때의 값
        int value_when_not_taken = dfs(idx + 1, leftWeight);

        // 값을 취했을 때의 값
        int value_when_taken = 0;
        if (weights[idx] <= leftWeight) {
            value_when_taken = values[idx] + dfs(idx + 1, leftWeight - weights[idx]);
        }
        
        // 담았을 때와 담지않았을 때의 가장 큰 가치를 dp에 저장하고 반환
        return dp[idx][leftWeight] = Math.max(value_when_not_taken, value_when_taken);
    }
}
