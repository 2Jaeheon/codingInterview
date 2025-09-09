import java.util.*;

public class Main{
    static int N;
    static int M;
    static int[][] info;
    static List<List<int[]>> adj;
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // N개의 도시
        // M개의 버스
        // A도시에서 B도시까지의 비용을 최소화
        
        N = sc.nextInt(); // 도시 개수
        M = sc.nextInt(); // 버스 개수
        info = new int[M][3];
        
        for(int i = 0; i < M; i++) {
            info[i][0] = sc.nextInt();
            info[i][1] = sc.nextInt();
            info[i][2] = sc.nextInt();
        }

        int src = sc.nextInt();
        int dst = sc.nextInt();

        // 인접리스트 세팅
        adj = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            int from = info[i][0];
            int to = info[i][1];
            int cost = info[i][2];

            adj.get(from).add(new int[]{to, cost});
        }

        System.out.println(dijkstra(src, dst));
    }

    static int dijkstra(int src, int dst){
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 100_000_000);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src}); // [비용, 정점 번호]
        dist[src] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curCost = cur[0];
            int curNode = cur[1];

            // dist보다 현재노드의 코스트가 더 크다면 패스
            if(dist[curNode] < curCost) {
                continue;
            }

            if(curNode == dst) {
                return curCost;
            }

            // 현재 노드와 연결된 다른 노드들을 확인
            for (int[] edge : adj.get(curNode)) {
                int v = edge[0]; // 이웃 노드
                int w = edge[1]; // u -> v 비용
    
                // 현재 노드를 거쳐서 이웃 노드로 이동하는 거리가 더 짧은 경우
                if (dist[v] > curCost + w) {
                    dist[v] = curCost + w;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        return dist[dst];
    }
}
