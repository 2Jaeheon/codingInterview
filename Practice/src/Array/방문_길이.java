package Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class 방문_길이 {
    //U: 위쪽으로 한 칸 가기
    //D: 아래쪽으로 한 칸 가기
    //R: 오른쪽으로 한 칸 가기
    //L: 왼쪽으로 한 칸 가기

    public static void main(String[] args) {
        String data = "ULURRDLLU";
        String data2 = "LULLLLLLU";
        System.out.println(solution2(data));
        System.out.println(solution2(data2));
    }

    public static int solution(String dirs) {
        int x = 0; // x좌표
        int y = 0; // y좌표

        Set<String> visitedPaths = new HashSet<>();

        for (int i = 0; i < dirs.length(); i++) {
            int nextX = x; //다음에 가야할 x좌표
            int nextY = y; //다음에 가야할 y좌표

            switch (dirs.charAt(i)) {
                case 'U':
                    nextY += 1;
                    break;
                case 'D':
                    nextY -= 1;
                    break;
                case 'R':
                    nextX += 1;
                    break;
                case 'L':
                    nextX -= 1;
                    break;
            }

            // 좌표평면의 경계를 벗어나는 경우 무시
            if (nextX < -5 || nextX > 5 || nextY < -5 || nextY > 5) {
                continue;
            }

            // 이동 경로 저장(0001이면 0,0에서 0,1로 이동)
            // (추가로 중복을 방지하기 위해 0,1 -> 0,0을 방지하기 위해 위와 반대도 set에 넣어줌)
            String path1 = x + "" + y + "" + nextX + "" + nextY;
            String path2 = nextX + "" + nextY + "" + x + "" + y;
            visitedPaths.add(path1);
            visitedPaths.add(path2);

            // 위치 업데이트
            x = nextX;
            y = nextY;
        }

        return visitedPaths.size() / 2; // 양방향 경로 저장이므로 2로 나눔
    }

    private static boolean isValidMove(int nx, int ny) {
        return 0 <= nx && nx < 11 && 0 <= ny && ny < 11;
    }

    static HashMap<Character, int[]> location = new HashMap<>();

    private static void initLocation() {
        location.put('U', new int[]{0, 1});
        location.put('D', new int[]{0, -1});
        location.put('L', new int[]{-1, 0});
        location.put('R', new int[]{1, 0});
    }

    public static int solution2(String dirs) {
        initLocation();
        int x = 5, y = 5;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < dirs.length(); i++) {
            //해시맵에서 배열에서 방향에 따라서 nextX와 nextY에 각각 저장한다.
            int[] offset = location.get(dirs.charAt(i));
            int nextX = x + offset[0];
            int nextY = y + offset[1];

            if (!isValidMove(nextX, nextY)) {
                continue;
            }

            //A->B랑 B->A모두 추가함.
            set.add(x + "" + y + "" + nextX + "" + nextY);
            set.add(nextX + "" + nextY + "" + x + "" + y);

            //좌표를 이동했으니까 갱신
            x = nextX;
            y = nextY;
        }

        return set.size() / 2;
    }

    // Node class to represent the state of each cell
    static class Node {

        boolean visitedFromUp;
        boolean visitedFromDown;
        boolean visitedFromRight;
        boolean visitedFromLeft;

        Node() {
            this.visitedFromUp = false;
            this.visitedFromDown = false;
            this.visitedFromRight = false;
            this.visitedFromLeft = false;
        }
    }

    public int solution3(String dirs) {
        // 2차원 배열을 사용하여 각 위치의 노드 상태를 저장
        Node[][] grid = new Node[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                grid[i][j] = new Node();
            }
        }

        int x = 5, y = 5; // 시작 위치 (5,5)
        int count = 0;

        for (char dir : dirs.toCharArray()) {
            int nx = x, ny = y;

            // 이동할 방향 결정
            switch (dir) {
                case 'U':
                    ny++;
                    break;
                case 'D':
                    ny--;
                    break;
                case 'R':
                    nx++;
                    break;
                case 'L':
                    nx--;
                    break;
            }

            // 경계 검사를 통해 유효한 위치인지 확인
            if (nx < 0 || nx > 10 || ny < 0 || ny > 10) {
                continue;
            }

            // 현재 위치의 노드를 가져옴
            Node currentNode = grid[x][y];
            Node nextNode = grid[nx][ny];

            // 방향에 따라 노드 상태 업데이트 및 방문 카운트 증가
            boolean newPath = false;
            if (dir == 'U' && !currentNode.visitedFromUp) {
                currentNode.visitedFromUp = true;
                nextNode.visitedFromDown = true;
                newPath = true;
            } else if (dir == 'D' && !currentNode.visitedFromDown) {
                currentNode.visitedFromDown = true;
                nextNode.visitedFromUp = true;
                newPath = true;
            } else if (dir == 'R' && !currentNode.visitedFromRight) {
                currentNode.visitedFromRight = true;
                nextNode.visitedFromLeft = true;
                newPath = true;
            } else if (dir == 'L' && !currentNode.visitedFromLeft) {
                currentNode.visitedFromLeft = true;
                nextNode.visitedFromRight = true;
                newPath = true;
            }

            // 새로운 경로인 경우 카운트 증가
            if (newPath) {
                count++;
            }

            // 현재 위치 업데이트
            x = nx;
            y = ny;
        }

        return count;
    }

    public int solution4(String dirs) {
        // 11x11x4 배열을 사용하여 각 위치에서 4방향으로의 이동을 표시
        // 0: 위, 1: 아래, 2: 오른쪽, 3: 왼쪽
        boolean[][][] visited = new boolean[11][11][4];
        int x = 5, y = 5; // 시작 위치 (0,0)을 (5,5)로 매핑
        int count = 0;

        for (char dir : dirs.toCharArray()) {
            int nx = x, ny = y, direction = 0;

            switch (dir) {
                case 'U':
                    ny++;
                    direction = 0;
                    break;
                case 'D':
                    ny--;
                    direction = 1;
                    break;
                case 'R':
                    nx++;
                    direction = 2;
                    break;
                case 'L':
                    nx--;
                    direction = 3;
                    break;
            }

            // 경계를 벗어나는 경우 무시
            if (nx < 0 || nx > 10 || ny < 0 || ny > 10) {
                continue;
            }

            // 처음 가는 길인 경우 카운트 증가
            if (!visited[x][y][direction]) {
                visited[x][y][direction] = true;
                // 반대 방향도 방문 처리
                visited[nx][ny][direction ^ 1] = true;
                count++;
            }

            x = nx;
            y = ny;
        }
        return count;
    }

}
