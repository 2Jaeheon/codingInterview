import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        int e = sc.nextInt();
        int k = sc.nextInt();
        
        List<List<Edge>> graph = new ArrayList<>();
        for(int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            graph.get(from).add(new Edge(to, cost));
        }

        // 시작점 자신은 0
        // i번 정점으로의 최단 거리
        dijkstra(k, graph, v);
    }

    public static void dijkstra(int start, List<List<Edge>> graph, int v) {
        // dist[i]: i까지의 최단거리
        int[] dist = new int[v + 1];
        Arrays.fill(dist, 10000000);
        dist[start] = 0;
        
        // {정점, 최단거리}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0]; // 정점
            int cost = cur[1]; // 정점에서의 최단거리

            // 인접한 것들을 순회하면서 
            for(Edge edge : graph.get(now)) {
                // 현재최단거리 + 다음에서의 최단거리
                int nextCost = cost + edge.cost;

                if(dist[edge.to] > nextCost) {
                    pq.offer(new int[]{edge.to, nextCost});
                    dist[edge.to] = nextCost;
                }
            }
        }

        for(int i = 1; i < dist.length; i++) {
            if(dist[i] == 10000000) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
