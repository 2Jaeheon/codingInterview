package graph.num39;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class 미로_탈출 {

    public static void main(String[] args) {
        String[] maps = new String[]{"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        System.out.println(solution(maps));
    }

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


    /*
     *
     *
     *
     *
     *
     * */

    // ❶ 위, 아래, 왼쪽, 오른쪽 이동 방향
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    // ❷ 위치 정보(x, y)를 저장할 클래스 생성
    private static class Point {

        int nx, ny;

        public Point(int nx, int ny) {
            this.nx = nx;
            this.ny = ny;
        }
    }

    private static char[][] map;
    private static int N, M;

    public int solution2(String[] maps) {
        N = maps.length;
        M = maps[0].length();

        // ❸ 미로에 대한 정보를 배열로 저장
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = maps[i].toCharArray();
        }

        Point start = null, end = null, lever = null;

        // ❹ 시작 지점, 출구 그리고 레버의 위치를 찾음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'S') {
                    start = new Point(j, i);
                } else if (map[i][j] == 'E') {
                    end = new Point(j, i);
                } else if (map[i][j] == 'L') {
                    lever = new Point(j, i);
                }
            }
        }

        // ❺ 시작 자점 -> 레버, 레버 -> 출구까지의 최단 거리를 각각 구함
        int startLever = bfs(start, lever);
        int leverEnd = bfs(lever, end);

        // ❻ 도착 불가능한 경우는 -1, 도착 가능한 경우는 최단 거리를 반환
        if (startLever == -1 || leverEnd == -1) {
            return -1;
        } else {
            return startLever + leverEnd;
        }
    }

    // start -> end 로 너비 우선 탐색하여 최단거리 반환
    private static int bfs(Point start, Point end) {
        // ❼ 너비 우선 탐색 초기 과정
        int[][] dist = new int[N][M];
        ArrayDeque<Point> queue = new ArrayDeque<>();

        dist[start.ny][start.nx] = 1;
        queue.add(start);

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            // ❽ 네 방향으로 이동
            for (int i = 0; i < 4; i++) {
                int nextX = now.nx + dx[i];
                int nextY = now.ny + dy[i];

                // ❾ 범위를 벗어나는 경우 예외 처리
                if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) {
                    continue;
                }

                // ➓ 이미 방문한 지점인 경우 탐색하지 않음
                if (dist[nextY][nextX] > 0) {
                    continue;
                }

                // ⓫ X가 아닌 지점만 이동 가능
                if (map[nextY][nextX] == 'X') {
                    continue;
                }

                // ⓬ 거리 1증가
                dist[nextY][nextX] = dist[now.ny][now.nx] + 1;

                // ⓭ 다음 정점을 큐에 넣음
                queue.add(new Point(nextX, nextY));

                // ⓮ 도착점에 도달하면 최단 거리를 반환
                if (nextX == end.nx && nextY == end.ny) {
                    return dist[end.ny][end.nx] - 1;
                }
            }
        }

        // ⓯ 탐색을 종료할 때까지 도착 지점에 도달하지 못 했다면 -1 반환
        return -1;
    }
}
