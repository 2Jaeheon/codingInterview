import java.util.*;

public class Main {
    // Integer.MAX_VALUE를 사용하면 덧셈 과정에서 오버플로우가 날 수 있으므로
    // 문제의 제약조건을 고려한 적당히 큰 값을 사용합니다. (100 * 15 = 1500 이 최대 거리)
    static final int INF = 100_000_000;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 지역 개수
        int m = sc.nextInt(); // 수색범위
        int r = sc.nextInt(); // 길의 개수

        int[] items = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            items[i] = sc.nextInt();
        }

        // 1. 2차원 배열 준비 및 초기화
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // 자기 자신으로 가는 거리는 0
        }

        // 입력받은 길 정보로 배열 채우기
        for (int i = 0; i < r; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int length = sc.nextInt();
            // 양방향이므로 양쪽 모두 값을 넣어줌
            dist[from][to] = length;
            dist[to][from] = length;
        }

        // 2. 플로이드-워셜 알고리즘 실행
        for (int k = 1; k <= n; k++) { // k: 거쳐가는 노드
            for (int i = 1; i <= n; i++) { // i: 출발 노드
                for (int j = 1; j <= n; j++) { // j: 도착 노드
                    // i에서 j로 바로 가는 것보다 k를 거쳐 가는 게 더 빠르다면 갱신
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 3. 결과 계산
        int maxItemCount = 0;
        // i번 지역에 떨어졌을 경우를 계산
        for (int i = 1; i <= n; i++) {
            int currentItemCount = 0;
            // j번 지역까지의 거리가 수색범위 m 이하인지 확인
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] <= m) {
                    currentItemCount += items[j];
                }
            }
            maxItemCount = Math.max(maxItemCount, currentItemCount);
        }

        System.out.println(maxItemCount);
    }
}