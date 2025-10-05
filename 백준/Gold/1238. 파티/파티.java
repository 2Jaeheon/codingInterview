import java.util.*;

public class Main{
    static class Edge {
        int from;
        int to;
        int time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // N명의 학생이 X번 마을에 모여서 파티를 벌임
        // M 개의 단방향 도로
        // i번째 길을 지날 때 Ti의 시간을 소비

        // 각 학생은 파티에 참석하기 위해 걸어갔다가 다시 자신의 마을로 가야함.
        // 최단 시간에 가고싶음.
        // 이 때, 가장 많은 시간을 소비하는 학생

        int n = sc.nextInt(); // 학생 수 1000
        int m = sc.nextInt(); // 도로 개수 10000
        int x = sc.nextInt(); // 파티 마을 번호

        // Math.max(가는데 걸리는 시간 + 오는데 걸리는 시간) 를 해야할 거 같음
        // 그럼 가는데 걸리는 시간을 따로 구하고, 오는데 걸리는 시간을 따로 구하면 될까
        // 어떻게 구해야하지??
        // 1. 다익스트라
        // 2. BFS
        // BFS는 최단 거리를 찾아내는거지 최단 비용 거리를 찾아내지는 않음.
        // 따라서 다익스트라로 풀어야함.
        // 가는 그래프와 오는 그래프를 만들어서 올 때 처리도 하도록 하면 될 거 같음.
        // 근데 시간초과 나는 거아냐?
        // 내가 생각한건 각 노드로부터 반복을 돌려서 X까지의 최단배열을 받아서 다시 또 처리하는 방식
        // 근데 복잡도가 너무 늘어남...
        // X를 기준으로 앞으로 뒤로 dijkstra 함수 두번 호출하면 될 거 같은데?
        // 그러면 reversedGraph를 만들어서 저장해놓고 사용하면 되지 않을까??
        // X에서 집으로 돌아가는건 Graph 그대로 구하면 되는데,
        // 집에서 X로 가는건 경우가 많으니까 반대로 돌리기만 하면 됨.
        

        List<List<Edge>> graph = new ArrayList<>();
        List<List<Edge>> reversedGraph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reversedGraph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int time = sc.nextInt();

            graph.get(from).add(new Edge(from, to, time));
            reversedGraph.get(to).add(new Edge(to, from, time));
        }

        int[] distFromX = dijkstra(graph, n, x); // X로부터 돌아오는 길
        int[] distToX = dijkstra(reversedGraph, n, x); // X로 가는 길

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            int totalTime = distFromX[i] + distToX[i];
            if (totalTime > maxTime) {
                maxTime = totalTime;
            }
        }
        
        System.out.println(maxTime);
    }

    public static int[] dijkstra(List<List<Edge>> graph, int n, int start) {
        // 최단 거리 정보를 저장할 배열
        int[] dist = new int[n + 1];
        // 모든 거리를 무한대로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        // 우선순위 큐를 사용하여 비용이 가장 적은 노드를 먼저 처리
        // int[] = {노드 번호, 시작점부터의 비용}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // 시작 노드 초기화
        dist[start] = 0;
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] currentNode = pq.poll();
            int currentVertex = currentNode[0];
            int currentCost = currentNode[1];

            // 이미 더 짧은 경로를 발견했다면 무시
            if (currentCost > dist[currentVertex]) {
                continue;
            }

            // 현재 노드와 연결된 모든 간선 확인
            for (Edge edge : graph.get(currentVertex)) {
                int newCost = dist[currentVertex] + edge.time;

                // 새로운 경로가 기존 경로보다 짧다면 정보 갱신
                if (newCost < dist[edge.to]) {
                    dist[edge.to] = newCost;
                    pq.offer(new int[]{edge.to, newCost});
                }
            }
        }
        
        return dist;
    }
}
