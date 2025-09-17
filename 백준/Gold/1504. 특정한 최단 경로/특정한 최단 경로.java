import java.util.*;

public class Main{
    static final long INF = Long.MAX_VALUE / 4;
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 방향성이 없는 그래프
        // 1에서 N까지 최단거리

        // 임의의 두 정점은 반드시 통과해야함.
        // 이동했던 점과 간선을 다시 이동할 수 있다.

        int N = sc.nextInt(); // Node
        int E = sc.nextInt(); // Edge

        // a <-> b, 거리: c
        // A와 B를 연결하면서 거리까지 저장해야함.
        List<List<Node>> list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int distance = sc.nextInt();

            list.get(from).add(new Node(from, to, distance));
            list.get(to).add(new Node(to, from, distance));
        }

        // 두 개의 정점을 무조건 지나야함.
        int firstNode = sc.nextInt();
        int secondNode = sc.nextInt();

        // 1에서 모든 정점까지의 거리
        long[] distFrom1  = dijkstra(1, list, N);
        // 첫 번째 경유지에서 모든 정점까지의 거리
        long[] distFromA  = dijkstra(firstNode, list, N);
        // 두 번째 경유지에서 모든 정점까지의 거리
        long[] distFromB  = dijkstra(secondNode, list, N);

        long path1 = INF; // path1 = 1→firstNode→secondNode→N
        if (distFrom1[firstNode] < INF && distFromA[secondNode] < INF && distFromB[N] < INF) {
            path1 = distFrom1[firstNode] + distFromA[secondNode] + distFromB[N];
        }
        
        long path2 = INF; // path2 = 1→secondNode→firstNode→N
        if (distFrom1[secondNode] < INF && distFromB[firstNode] < INF && distFromA[N] < INF) {
            path2 = distFrom1[secondNode] + distFromB[firstNode] + distFromA[N];
        }
        
        long answer = Math.min(path1, path2);
        System.out.println(answer >= INF ? -1 : answer);
    }

    // 특정한 출발점에서 시작했을 때, 모든 정점까지의 최단거리를 구해주는 도구
    static long[] dijkstra(int start, List<List<Node>> graph, int N) {
        // dist[i]는 i까지의 최단 거리
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF); // 초기에는 무한으로 초기화
        dist[start] = 0;
        
        // pq[]에는 {정점, 현재 거리}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{start, 0});

        while(!pq.isEmpty()) {
            long[] cur = pq.poll();
            
            int now = (int) cur[0];
            long d = cur[1];

            if(d != dist[now]) {
                continue;
            }

            // 근처의 모든 다음
            for(Node edge : graph.get(now)){
                long nd = d + edge.distance;

                // edge.to까지의 최단거리보다 nd가 작다면 pq에 추가
                // 즉 최단거리를 갱신해줘야함.
                if (nd < dist[edge.to]) {
                    dist[edge.to] = nd;
                    pq.offer(new long[]{edge.to, nd});
                }
            }
        }
        return dist;
    }


    static class Node {
        int from;
        int to;
        int distance;

        public Node(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }
}
