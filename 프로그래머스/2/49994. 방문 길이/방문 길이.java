import java.util.*;
class Solution {
    public int solution(String dirs) {
        // 11x11x4 배열을 사용하여 각 위치에서 4방향으로의 이동을 표시
        // 0: 위, 1: 아래, 2: 오른쪽, 3: 왼쪽
        boolean[][][] visited = new boolean[11][11][4];
        int x = 5, y = 5; // 시작 위치 (0,0)을 (5,5)로 매핑
        int count = 0;

        for (char dir : dirs.toCharArray()) {
            int nx = x, ny = y, direction = 0;

            switch (dir) {
                case 'U':
                    ny++; direction = 0; break;
                case 'D':
                    ny--; direction = 1; break;
                case 'R':
                    nx++; direction = 2; break;
                case 'L':
                    nx--; direction = 3; break;
            }

            // 경계를 벗어나는 경우 무시
            if (nx < 0 || nx > 10 || ny < 0 || ny > 10) continue;

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