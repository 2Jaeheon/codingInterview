import java.util.*;
class Solution {
    static class Node {

        int row, column;

        public Node(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public static int solution(String[] maps) {
        // S -> L -> E로 가면 됨.
        final int[] dx = {0, 0, -1, 1};
        final int[] dy = {-1, 1, 0, 0};

        final int N = maps.length;
        final int M = maps[0].length();

        Node start = null, lever = null, exit = null;

        int[][] graph = new int[N][M];
        int[][] visited = new int[N][M]; // 방문 여부 및 거리 저장

        // S, L, E 위치 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                switch (maps[i].charAt(j)) {
                    case 'S':
                        start = new Node(i, j);
                        graph[i][j] = -1;
                        break;
                    case 'L':
                        lever = new Node(i, j);
                        graph[i][j] = 2;
                        break;
                    case 'E':
                        exit = new Node(i, j);
                        graph[i][j] = 3;
                        break;
                    case 'X':
                        graph[i][j] = 1; // 벽
                        break;
                    default:
                        graph[i][j] = 0; // 통로
                        break;
                }
            }
        }

        // S -> L 거리
        int toLever = bfs(start, lever, graph, visited, N, M, dx, dy);
        if (toLever == -1) {
            return -1;
        }

        // 재방문을 위한 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = 0;
            }
        }

        // L -> E 거리
        int toExit = bfs(lever, exit, graph, visited, N, M, dx, dy);
        if (toExit == -1) {
            return -1;
        }

        return toLever + toExit;
    }

    private static int bfs(Node start, Node target, int[][] graph, int[][] visited, int N, int M,
        int[] dx, int[] dy) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start);
        // 이동한 거리를 기록해야하므로 0부터 시작 -> 이동하면 1로 증가!
        visited[start.row][start.column] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int currentDistance = visited[now.row][now.column];

            if (now.row == target.row && now.column == target.column) {
                return currentDistance;
            }

            for (int i = 0; i < 4; i++) {
                int newX = now.row + dx[i];
                int newY = now.column + dy[i];

                // 범위 체크, 벽 체크, 방문 체크
                if (newX >= 0 && newX < N && newY >= 0 && newY < M && graph[newX][newY] != 1
                    && visited[newX][newY] == 0) {
                    queue.add(new Node(newX, newY));
                    visited[newX][newY] = currentDistance + 1;
                }
            }
        }

        return -1; // 도달 불가능
    }
}