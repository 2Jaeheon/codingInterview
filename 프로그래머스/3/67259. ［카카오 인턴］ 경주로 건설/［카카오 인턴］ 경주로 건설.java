import java.util.*;
class Solution {
    public static class Point implements Comparable<Point> {

        int x;
        int y;
        int dir;
        int cost;

        public Point(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }

    public static int solution(int[][] board) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0, 0, -1, 0));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int N = board.length;

        int[][][] cost = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }

        while (!pq.isEmpty()) {
            Point cur = pq.poll(); // 현재 위치

            for (int i = 0; i < 4; i++) { // 4방향 탐색
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // 지나갈 수 없으면 Continue;
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 1) {
                    continue;
                }

                int newCost = cur.cost + 100; // 100원 추가

                if (cur.dir != -1 && cur.dir != i) {
                    newCost += 500; // 방향이 바뀌면 코너 비용 추가
                }

                if (newCost < cost[nx][ny][i]) { // 더 작은 비용으로 갱신 가능하면 업데이트
                    cost[nx][ny][i] = newCost;
                    pq.add(new Point(nx, ny, i, newCost));
                    //printBoard(board, cost, N);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, cost[N - 1][N - 1][i]);
        }
        return answer;
    }

    public static void printBoard(int[][] board, int[][][] cost, int N) {
        System.out.println("======== 경주로 진행 상황 ========");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean visited = false;
                for (int k = 0; k < 4; k++) {
                    if (cost[i][j][k] != Integer.MAX_VALUE) {
                        visited = true;
                        break;
                    }
                }
                if (board[i][j] == 1) {
                    System.out.print("X ");  // 장애물
                } else if (visited) {
                    System.out.print(". ");  // 방문한 위치
                } else {
                    System.out.print("0 ");  // 지나가지 않은 길
                }
            }
            System.out.println();
        }
        System.out.println("=================================");
    }
}