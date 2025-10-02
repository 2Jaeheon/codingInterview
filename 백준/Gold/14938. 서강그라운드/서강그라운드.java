import java.util.*;

public class Main{
    static int n;
    static int m;
    static int r;
    static List<List<Edge>> graph;
    static Map<Integer, Integer> itemInfo;
    
    static class Edge {
        int to;
        int length;

        public Edge(int to, int length) {
            this.to = to;
            this.length = length;
        }
    }
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        r = sc.nextInt();

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
            int len = sc.nextInt();

            graph.get(from).add(new Edge(to, len));
            graph.get(to).add(new Edge(from, len));
        }

        // dijkstra
        int answer = 0;
        for(int i = 1; i <= n; i++) {
            int[] dist = dijkstra(i);
            int sum = 0;
            
            for(int j = 0; j < dist.length; j++) {
                if(dist[j] <= m) {
                    sum += itemInfo.get(j);
                }
            }
            
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }

    public static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 100_000_000);
        
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.length));
        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curTo = cur.to;
            int curLen = cur.length;

            if(curLen > dist[curTo])
                continue;

            for(Edge next : graph.get(curTo)) {
                int nextLength = curLen + next.length;

                if(nextLength < dist[next.to]) {
                    dist[next.to] = nextLength;
                    pq.offer(new Edge(next.to, nextLength));
                }
            }
        }

        return dist;
    }

    
}
