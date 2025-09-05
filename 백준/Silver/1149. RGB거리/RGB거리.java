import java.util.*;

public class Main{

    static int n;
    static int[][] cost;
    static int[][] memo;
    static final int INF = 1000000000;
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        cost = new int[n][3];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0;j < 3; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        // 메모 배열: -1은 아직 계산 안 함을 의미
        // memo[i][j]는 i인덱스에 대해서 특정 j색상으로 칠할 때 최소비용을 저장해주는 용도
        memo = new int[n][3];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        // 시작 색(R/G/B)을 각각 시도 → 그 중 최소
        int ans = Math.min(Math.min(solve(0, 0), solve(0, 1)), solve(0, 2));
        System.out.println(ans);
    }

    // idx번째 집을 특정 색으로 칠했을 때, 끝까지 칠하는 최소 비용
    static int solve(int idx, int color) {
        // 이미 집을 특정 색으로 칠했을 때 최소비용을 계산해놓은 적이 있다면 그대로 반환해줌
        if (memo[idx][color] != -1) {
            return memo[idx][color];
        }

        // 마지막 집이라면 현재 집을 색으로 칠하는 비용으로 저장
        if (idx == n - 1) {
            return memo[idx][color] = cost[idx][color];
        }

        // 다음 집(idx+1)에서 선택 가능한 색은 현재색상을 제외한 두 가지
        int nextMin = INF;
        for (int nextColor = 0; nextColor < 3; nextColor++) {
            // 인접 동일 색 금지
            if (nextColor == color) {
                continue;
            }
            
            nextMin = Math.min(nextMin, solve(idx + 1, nextColor));
        }

        // 현재 집을 color로 칠한 비용 + (다음 집부터의 최소 비용)
        // 현재의 최적 선택은 “현재 비용 + 다음 단계의 최적 부분해 최소값”
        return memo[idx][color] = cost[idx][color] + nextMin;
    }
}
