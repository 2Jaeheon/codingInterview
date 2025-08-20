import java.util.*;

public class Main {
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] grid = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int[] ans = new int[]{0}; // 정답을 담는 가변 래퍼(전역 대신 지역 참조로 공유)

        // 모든 시작점에서 DFS + 'ㅗ' 모양 검사
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                visited[x][y] = true;
                dfs(n, m, grid, visited, x, y, 1, grid[x][y], ans);
                visited[x][y] = false;

                // T(ㅗ) 모양은 별도 처리
                ans[0] = Math.max(ans[0], checkT(n, m, grid, x, y));
            }
        }

        System.out.println(ans[0]);
    }

    // 깊이 4까지 경로형 도형을 모두 탐색 (일자/ㄱ/ㅁ 파생 대부분 커버)
    private static void dfs(int n, int m, int[][] grid, boolean[][] visited,
                            int x, int y, int depth, int sum, int[] ans) {
        if (depth == 4) {
            if (sum > ans[0]) {
                ans[0] = sum;
            }
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if (!inRange(n, m, nx, ny) || visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(n, m, grid, visited, nx, ny, depth + 1, sum + grid[nx][ny], ans);
            visited[nx][ny] = false;
        }
    }

    // 'ㅗ'(T) 모양 계산: 중심 + 인접 3칸(혹은 4칸 중 최소 하나 제거)
    private static int checkT(int n, int m, int[][] grid, int x, int y) {
        int arms = 0;
        int sum = grid[x][y];
        int minArm = Integer.MAX_VALUE;

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if (!inRange(n, m, nx, ny)) continue;

            arms++;
            sum += grid[nx][ny];
            if (grid[nx][ny] < minArm) minArm = grid[nx][ny];
        }

        if (arms < 3) return 0;          // T 모양 불가
        if (arms == 3) return sum;       // 팔 3개면 그대로 T
        return sum - minArm;             // 팔 4개면 가장 작은 팔 하나 제거
    }

    private static boolean inRange(int n, int m, int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}