import java.util.*;
public class Main{
    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    
    public static int n, s, e, t, m, w;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (TC-- > 0) {
            int N = sc.nextInt(); // 지점의 수
            int M = sc.nextInt(); // 도로의 개수
            int W = sc.nextInt(); // 웜홀의 개수

            List<Edge> edges = new ArrayList<>();

            // 도로 정보 입력
            for (int i = 0; i < M; i++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                int t = sc.nextInt();
                edges.add(new Edge(s, e, t));
                edges.add(new Edge(e, s, t));
            }

            // 웜홀 정보 입력
            for (int i = 0; i < W; i++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                int t = sc.nextInt();
                edges.add(new Edge(s, e, -t));
            }
            
            // bellmanFord 로직을 호출하고 결과를 처리하는 부분은 동일
            if (bellmanFord(N, edges)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.print(sb.toString());
    }

    private static boolean bellmanFord(int N, List<Edge> edges) {
        int[] dist = new int[N + 1];
        final int INF = 100_000_000; 
        Arrays.fill(dist, 0);
        
        dist[1] = 0;

        // 최단 거리 갱신
        for (int i = 1; i < N; i++) {
            for (Edge edge : edges) {
                // 아직 도달하지 않은 노드(INF)에서는 갱신을 시작하지 않음
                if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        } 
        
        // N번째 갱신을 통해 음수 사이클 존재 여부 최종 확인
        for (Edge edge : edges) {
            if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                return true; // N번째에도 갱신이 발생하면 음수 사이클이 존재
            }
        }

        return false; // 음수 사이클이 없음
    }
}
