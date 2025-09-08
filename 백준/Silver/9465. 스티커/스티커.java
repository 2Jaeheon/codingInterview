import java.util.*;

public class Main{

    static int n;
    static int[][] stickers;
    static int[][] memo;
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 2n개의 스티커 중에서 점수의 합이 최대가 되면서 서로 변을 공유 하지 않는 스티커 집합을 구해야 한다.

        int T = sc.nextInt(); // 테스트 케이스 개수

        // testCase
        for(int testCase = 0; testCase < T; testCase++) {
            // input
            n = sc.nextInt(); // 2행 n열
            stickers = new int[2][n];

            // init
            for(int i = 0; i < stickers.length; i++) {
                for(int j = 0; j < stickers[0].length; j++) {
                    stickers[i][j] = sc.nextInt();
                }
            }

            // DFS 기반의 memoization을 통해서 가능하지 않을까?
            memo = new int[2][n];
            for(int i = 0; i < 2; i++) {
                Arrays.fill(memo[i], -1);
            }

            int result1 = dfs(0, n - 1);
            int result2 = dfs(1, n - 1);
            System.out.println(Math.max(result1, result2));
        }
    }

    // dfs(r, c): row, col의 위치의 스티커를 떼어낼 때 0열부터 col열까지 얻을 수 있는 점수의 최댓값
    public static int dfs(int row, int col) {
        // col이 0보다 작으면 점수가 없음
        if (col < 0) {
            return 0;
        }

        // memoization
        if (memo[row][col] != -1) {
            return memo[row][col];
        }

        // 점화식을 이용한 재귀 호출
        int prev_col_opposite = dfs(1 - row, col - 1); // col-1 열의 반대편 스티커를 뗐을 경우
        int prev_prev_col_max = Math.max(dfs(0, col - 2), dfs(1, col - 2)); // col-1 열을 건너뛴 경우

        // 현재 스티커 점수와 이전 상태의 최댓값을 더해서 저장
        memo[row][col] = stickers[row][col] + Math.max(prev_col_opposite, prev_prev_col_max);
        return memo[row][col];
    }
}
