import java.util.*;

public class Main {
    public static final int[] dx = new int[]{-1, 1, 0, 0};
    public static final int[] dy = new int[]{0, 0, -1, 1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] graph = new int[n +1][n + 1];
        
        for(int i = 0; i < n; i++) {
            String str = sc.next();
            for(int j = 0; j < n; j++) {
                graph[i][j] = str.charAt(j) - '0';
            }
        }

        // 하나씩 그래프의 모든 점을 순회해보면서 방문했는지 안했는지를 체크
        // 방문하지 않았다면 BFS를 돌려서 인근에 위치한 모든 점들을 체크
        // division[] 배열을 통해서 새로운 조합마다 + 1을 해서 추가해줌.
        int[][] division = new int[n+1][n+1];
        int count = 1;
        for(int i = 0; i <= n; i++) {
            Arrays.fill(division[i], -1);
        }

        List<Integer> houseCounts = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // 아직 방문하지 않았고, 집이 있는 곳만 처리
                if(division[i][j] == -1 && graph[i][j] == 1) {
                    int houseCount = bfs(graph, division, i, j, n, count++);
                    houseCounts.add(houseCount);
                }
            }
        }   

        System.out.println(houseCounts.size());
        Collections.sort(houseCounts);
        for (int houseCount : houseCounts) {
            System.out.println(houseCount);
        }
    }

    public static int bfs(int[][] graph, int[][] division, int x, int y, int n, int count) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        division[x][y] = count;
        int houseCount = 1;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nextX = cur[0] + dx[i];
                int nextY = cur[1] + dy[i];

                if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                    // 아직 방문하지 않았고, 집이 있는 곳만 처리
                    if (graph[nextX][nextY] == 1 && division[nextX][nextY] == -1) {
                        queue.offer(new int[]{nextX, nextY});
                        division[nextX][nextY] = count;
                        houseCount++;
                    }
                }
            }
        }

        return houseCount;
    }
}