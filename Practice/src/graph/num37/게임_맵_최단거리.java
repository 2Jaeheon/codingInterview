package graph.num37;

import java.util.ArrayDeque;
import java.util.Arrays;

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

}