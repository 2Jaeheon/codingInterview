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
        System.out.println(solution(data));
        System.out.println(solution(data2));
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
}
