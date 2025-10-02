import java.util.*;

public class Main{
    static int n;
    static int m;
    static int r;
    static List<List<Edge>> graph;
    static Map<Integer, Integer> itemInfo;
    
    static class Edge {
        int to;
        int value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 지역 개수
        m = sc.nextInt(); // 수색범위
        r = sc.nextInt(); // 길의 개수
        
        // 특정 노드로부터 최단거리를 알고있으면 됨
        // 그걸 모든 노드 (최대 100)에서 다익스트라를 통해서 구하면 되지 않을까?
        // 각 노드로부터 다익스트라 로직을 돌리면 되겠는데??

        graph = new ArrayList<>();
        itemInfo = new HashMap<>();
        
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 1; i <= n; i++) {
            itemInfo.put(i, sc.nextInt());
        }

        for(int i = 0; i < r; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int length = sc.nextInt();

            graph.get(from).add(new Edge(to, length));
            graph.get(to).add(new Edge(from, length));
        }

        // 다익스트라를 돌려야 함.
        int max = 0;
        for(int curNode = 1; curNode <= n; curNode++) {
            int[] answer = dijkstra(curNode);
            int sum = 0;
            
            for(int i = 1; i < answer.length; i++) {
                if(answer[i] <= m) {
                    sum += itemInfo.get(i);
                }
            }

            max = Math.max(sum, max);
        }

        System.out.println(max);
    }

    public static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 100_000_000);
        dist[start] = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            int curTo = cur.to;
            int curLen = cur.value;

            if(curLen > dist[curTo]) {
                continue;
            }

            // 인접한 다른 길을 탐색
            for(Edge next : graph.get(curTo)) {
                int totalLength = curLen + next.value;

                if(totalLength < dist[next.to]) {
                    dist[next.to] = totalLength;
                    pq.offer(new Edge(next.to, totalLength));
                }
            }
        }
        
        return dist;
    }
}
