import java.util.*;

public class Main{
    static final int[] dx = new int[]{-1, 1, 0, 0};
    static final int[] dy = new int[]{0, 0, -1, 1};
    
    static class Node {
        int x;
        int y;
        int status;

        public Node(int x, int y, int status) {
            this.x = x;
            this.y = y;
            this.status = status;
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] map = new int[n][m];
        List<Node> cheeses = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] == 1) { // 치즈라면
                    cheeses.add(new Node(i, j, 1));
                }
            }
        }

        // 외부와 접촉했는지 확인이 필요함.
        // 근데 내부 공기는 외부 공기가 아니라는 점에서
        // 내부 공기에 대해 카운트를 하면 안 됨.
        // 그렇다면 내부 공기인지 외부공기인지 어떻게 파악할 수 있을까?
        // (0, 0)에서 BFS를 돌려서 1로 바꿔본다음에 0이 남아있는 영역은 내부 공기의 영역 아닐까??
        // 그러면 이거 하는 함수를 하나 만들어놓자.
        // 그렇게 해서 내부 공기에 대한 정보만 따로 좌표로 정리하면 좋지 않을까
        // 이렇게 정리된 좌표를 계속 각 시간마다 함수호출해서 인접했는지를 확인하면 되겠네.

        // 그러면 이제 두 개의 외부공기와 접촉했는지는 어떻게 처리할까?
        // 그냥 맵에서 0인걸 확인하면 되지 않을까?
        // 어차피 내부 공기는 알고있으니까 다시 배열을 만들어서 처리할 수 있을 거 같은데
        // 아 근데 3차원 배열로도 풀 수 있을 거 같은데...

        // 자 그러면 어떻게 풀지 한 번 가이드를 짜보자.
        // 1. 내부 공기에 대해서 기록해놓는 함수 작성
        // 2. 각 치즈들에 대해서 인접한 외부공기의 개수를 찾아서 2보다 크면 map을 갱신해줌.
        // 3. 치즈가 모두 없어진 시간을 반환

        int clock = 0;
        
        // 치즈가 없을 때까지 반복하자.
        while(!cheeses.isEmpty()) {
            List<Node> innerAir = findInnerAir(cheeses, map);

            List<Node> toMelt = new ArrayList<>();

            // 이제 인접한 외부 공기를 찾아서 녹을 치즈를 찾자
            for(Node cur : cheeses) {
                int outside = 0;
                for(int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];

                    // 외부 공기가 2개 이상인지를 탐색해야함.
                    if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                        
                        // 외부 공기 판정
                        boolean isInner = false;
                        for (Node air : innerAir) {
                            if (air.x == nx && air.y == ny) {
                                isInner = true;
                                break;
                            }
                        }
                        // 인접한 노드가 0이고, 내부공기가 아니라면 외부공기를 증가.
                        if (map[nx][ny] == 0 && !isInner) {
                            outside++;
                        }
                    }
                }

                if(outside >= 2) {
                    toMelt.add(cur);
                }
            }

            // 녹은 것이 없다면 종료
            if(toMelt.isEmpty()) {
                break;
            }
                
            // 치즈 녹이기
            for (Node c : toMelt) {
                map[c.x][c.y] = 0;
            }

            // 치즈 리스트에서 제거하기
            cheeses.removeIf(cur -> map[cur.x][cur.y] == 0);
            clock++;
        }

        System.out.println(clock);
    }

    // 내부 공기에 대해서 찾아냄.
    public static List<Node> findInnerAir(List<Node> cheeses, int[][] map) {
        // 내부 공기를 저장할 리스트
        List<Node> innerAir = new ArrayList<>();
        int n = map.length;
        int m = map[0].length;
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 0));
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        int[][] tempMap = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                tempMap[i][j] = map[i][j];
            }
        }
        
        tempMap[0][0] = 2;

        while(!queue.isEmpty()) {
            Node curNode = queue.poll();
            int curX = curNode.x;
            int curY = curNode.y;
            
            for(int dir = 0; dir < 4; dir++) {
                int nextX = curX + dx[dir];
                int nextY = curY + dy[dir];

                if (0 <= nextX && nextX < n && 0 <= nextY && nextY < m && !visited[nextX][nextY] && tempMap[nextX][nextY] == 0) {
                    tempMap[nextX][nextY] = 2;
                    queue.offer(new Node(nextX, nextY, 1));
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(tempMap[i][j] == 0) {
                    innerAir.add(new Node(i, j, 0));
                }
            }
        }

        return innerAir;
    }
}
