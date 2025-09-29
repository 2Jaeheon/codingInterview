    import java.util.*;
    
    public class Main{
    
        static int n;
        static int m;
        static List<List<Edge>> graph;
        static class Edge {
            int to;
            int weight;
    
            public Edge(int to, int weight) {
                this.to = to;
                this.weight = weight;
            }
        }
        
        public static void main(String args[]){
            Scanner sc = new Scanner(System.in);
    
            // n개의 도시
            // m개의 버스 (각 버스마다 비용이 있음)
            // A -> B 도시로갈 때 비용의 최솟값
    
            n = sc.nextInt(); // 도시 개수
            m = sc.nextInt(); // 버스 개수
    
            // 버스정보가 순차적으로 주어짐.
            // 전형적인 그래프 문제로 보임
            // 최단 거리로 가는 비용을 출력해야함.
            // 다익스트라가 제일 좋지 않을까?
            // 근데 문제가 대놓고 플로이드인데? 플로이드-워셜 아닐까?
            // 일단 플로이드 워셜 푸는법 기억이 안 남..;;
    
            // 다익스트라로 최대한 풀어보자.
            // 일단 버스정보를 저장할 데이터 구조가 필요함
            graph = new ArrayList<>();
            for(int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
    
            for(int i = 0; i < m; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                int weight = sc.nextInt();
    
                graph.get(from).add(new Edge(to, weight));
            }
    
            for (int i = 1; i <= n; i++) {
                int[] answer = dijkstra(i);
            
                for (int j = 1; j <= n; j++) {
                    // 도달할 수 없는 경우(초기값 그대로인 경우) 0을 출력
                    if (answer[j] == 100_000_000) {
                        System.out.print("0 ");
                    } else {
                        System.out.print(answer[j] + " ");
                    }
                }
                System.out.println();
            }
        }
    
        public static int[] dijkstra(int start) {
            int dist[] = new int[n + 1];
            Arrays.fill(dist, 100_000_000);
    
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
            pq.offer(new Edge(start, 0));
            dist[start] = 0;
            
            while(!pq.isEmpty()) {
                Edge cur = pq.poll();
                int curCity = cur.to;
                int weight = cur.weight;
    
                if(weight > dist[curCity]) {
                    continue;
                }
    
                for(Edge next : graph.get(curCity)) {
                    int nextWeight = weight + next.weight;
    
                    if(nextWeight < dist[next.to]) {
                        dist[next.to] = nextWeight;
                        pq.offer(new Edge(next.to, nextWeight));
                    }
                }
            }
            
            return dist;
        }
    }
