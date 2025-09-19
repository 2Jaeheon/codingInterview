import java.util.*;

public class Main{
    public static int[] dx = new int[]{-1, 1, 0, 0};
    public static int[] dy = new int[]{0, 0, -1, 1};
    public static int maxCount = 0;
    public static int r, c;
    public static char[][] arr;
    public static boolean[] visited;

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();

        // {R, C}의 보드가 존재
        // 지나온 모든 알파벳과 달라야하고, 지나갈 수 있는 최댓값을 구해야함.
        // 최장거리를 구해야함. 아마도 dfs 기반일 것 같음
        // 값이 존재하지 않고 최단거리가 아니니 다익스트라는 아닌듯

        // 우선 지금까찌 지니간 칸을 체크해줘야함.
        // 다음에 진행이 가능한 것들 중 이미 지난 칸은 패스
        // dfs를 통해서 최댓값을 구하기만 하면 됨.

        arr = new char[r][c];
        for(int i = 0; i < r; i++) {
            String str = sc.next();
            for(int j = 0; j < str.length(); j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        visited = new boolean[26];
        visited[arr[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(maxCount);
    }

    // x, y, count: 지금까지 지나온 값
    public static void dfs(int x, int y, int count) {
        maxCount = Math.max(maxCount, count);

        for(int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(nextX >= 0 && nextX <= r - 1 && nextY >= 0 && nextY <= c - 1) {
                if(!visited[arr[nextX][nextY] - 'A']) {
                    visited[arr[nextX][nextY] - 'A'] = true;
                    dfs(nextX, nextY, count + 1);
                    visited[arr[nextX][nextY] - 'A'] = false;
                }
            }
        }
    }
}
