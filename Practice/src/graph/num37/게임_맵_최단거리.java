package graph.num37;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 게임_맵_최단거리 {

    public static void main(String[] args) {
        int[][] maps = new int[][]{
            {1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1},
            {0, 0, 0, 0, 1}
        };

        System.out.println("최단 거리: " + solution(maps));
    }
/*
    public static int solution(int[][] maps) {
        // 시작 지점: (0,0), 종료 지점: 게임 맵 우측 하단

        // 오른쪽 -> 왼쪽 -> 아래 -> 위 순으로 이동
        final int[] rx = {0, 0, 1, -1};
        final int[] ry = {1, -1, 0, 0};

        class Node {

            int row, column;

            public Node(int r, int c) {
                this.row = r;
                this.column = c;
            }
        }

        int N = maps.length;
        int M = maps[0].length;
        int[][] dist = new int[N][M];

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.addLast(new Node(0, 0));
        dist[0][0] = 1;

        while (!queue.isEmpty()) {
            Node now = queue.pollFirst();

            for (int i = 0; i < 4; i++) {
                int newRow = now.row + rx[i];
                int newColumn = now.column + ry[i];

                // 범위를 벗어난 경우
                if (newRow < 0 || newColumn < 0 || newRow >= N || newColumn >= M) {
                    continue;
                }

                // 벽인 경우
                if (maps[newRow][newColumn] == 0) {
                    continue;
                }

                // 처음 방문한다면 큐에 추가하고 거리를 갱신
                if (dist[newRow][newColumn] == 0) {
                    queue.addLast(new Node(newRow, newColumn));
                    dist[newRow][newColumn] = dist[now.row][now.column] + 1;

                    // 현재 dist 배열 상태 출력
                    printDist(dist);
                }
            }
        }

        return (dist[N - 1][M - 1] == 0) ? -1 : dist[N - 1][M - 1];
    }

    // dist 배열 출력 함수
    private static void printDist(int[][] dist) {
        System.out.println("현재 dist 배열 상태:");
        for (int[] row : dist) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("----------------------------");
    }

    public static int solution2(int[][] maps) {
        int[] dx = {0, 0, 1, -1}; // 이동 방향 (우, 좌, 하, 상)
        int[] dy = {1, -1, 0, 0};

        int N = maps.length;
        int M = maps[0].length;
        int[][] dist = new int[N][M];

        // 거리 배열을 최댓값으로 초기화
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        pq.offer(new int[]{0, 0, 1}); // {row, col, cost}
        dist[0][0] = 1;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int r = now[0], c = now[1], cost = now[2];

            // 도착하면 거리 반환
            if (r == N - 1 && c == M - 1) {
                return cost;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];

                // 범위 확인
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }

                // 벽이면 패스
                if (maps[nr][nc] == 0) {
                    continue;
                }

                // 더 짧은 경로가 있으면 갱신 후 큐에 추가
                int newCost = cost + 1;
                if (newCost < dist[nr][nc]) {
                    dist[nr][nc] = newCost;
                    pq.offer(new int[]{nr, nc, newCost});
                }
            }
        }
        return -1; // 도달 불가능한 경우
    }*/

    static int[] x = new int[]{1, -1, 0, 0};
    static int[] y = new int[]{0, 0, -1, 1};

    static class Node {

        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


    public static int solution(int[][] maps) {
        final int N = maps.length;
        final int M = maps[0].length;

        int[][] graph = new int[N][M];
        for (int[] row : graph) {
            Arrays.fill(row, 0);
        }

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0));
        graph[0][0] = 1;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = now.row + x[i];
                int newCol = now.col + y[i];

                if (newRow < 0 || newCol < 0 || newRow >= N || newCol >= M) {
                    continue;
                }

                if (maps[newRow][newCol] == 0) {
                    continue;
                }

                if (graph[newRow][newCol] == 0) {
                    queue.offer(new Node(newRow, newCol));
                    graph[newRow][newCol] = graph[now.row][now.col] + 1;
                }
            }
        }

        return (graph[N - 1][M - 1] == 0) ? -1 : graph[N - 1][M - 1];
    }
}