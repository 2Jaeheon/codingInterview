import java.util.*;

public class Main {

    public static int[] dx = new int[]{-1, 1, 0, 0};
    public static int[] dy = new int[]{0, 0, -1, 1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] IPosition = new int[2];

        char[][] graph = new char[n][m];
        for(int i = 0; i < n; i++) {
            String str = sc.next();
            for(int j = 0; j < m; j++) {
                graph[i][j] = str.charAt(j);

                if(graph[i][j] == 'I') {
                    IPosition[0] = i;
                    IPosition[1] = j;
                }
            }
        }


        // DFS
        int meetCount = 0; // 만난 사람의 수
        boolean[][] visited = new boolean[n][m]; // 방문처리를 위한 배열
        Stack<Point> stack = new Stack<>();

        int startX = IPosition[0];
        int startY = IPosition[1];
        Point startPoint = new Point(startX, startY);
        visited[startX][startY] = true;
        stack.push(startPoint);


        // BFS 시작
        while(!stack.isEmpty()) {
            Point cur = stack.pop();

            // 네 번 반복해서 상하좌우로 탐색
            for(int k = 0; k < 4; k++) {
                // 다음으로 가야할 X, Y
                int nextX = cur.x + dx[k];
                int nextY = cur.y + dy[k];

                // 만약 끝지점이라면 continue
                if(nextX < 0 || nextX > n-1 || nextY < 0 || nextY > m-1) {
                    continue;
                }

                // 갈 곳이 벽이라면 continue
                if(graph[nextX][nextY] == 'X') {
                    continue;
                }
                
                // 만약 nextX, nextY를 방문하지 않았따면
                if(!visited[nextX][nextY]) {
                    // 스택에 넣고 방문처리를 함.
                    stack.push(new Point(nextX, nextY));
                    visited[nextX][nextY] = true;

                    if(graph[nextX][nextY] == 'P') {
                        meetCount++;
                    }
                }
            }
        }
        if(meetCount == 0) {
            System.out.println("TT");
            return ;
        }
        System.out.println(meetCount);
    }

    public static class Point{
        int x;
        int y;

        public Point() {
            
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}