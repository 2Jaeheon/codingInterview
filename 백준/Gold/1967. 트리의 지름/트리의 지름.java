import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        // 가장 먼 A를 dfs로 찾고,
        // A에서 다시 가장 먼 정점을 dfs를 통해서 찾으면 됨.
        List<List<Edge>> graph = new ArrayList<>();
        
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < n - 1; i++) {
            int parent = sc.nextInt();
            int child = sc.nextInt();
            int distance = sc.nextInt();

            graph.get(parent).add(new Edge(child, distance));
            graph.get(child).add(new Edge(parent, distance));
        }

        // 1) 1에서 가장 먼 A 찾기
        int[] A = dfs(1, graph, n);        

        // 2) A에서 다시 가장 먼 노드까지의 거리 = 지름
        int[] B = dfs(A[0], graph, n);

        System.out.println(B[1]);
    }

    // start로부터 가장 길이가 긴 점을 구해야함.
    public static int[] dfs(int start, List<List<Edge>> graph, int n) {
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];

        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        dist[start] = 0;

        while(!stack.isEmpty()) {
            int v = stack.pop();
            
            for(Edge e : graph.get(v)) {
                // 방문하지 않았다면 갱신
                if (!visited[e.to]) {
                    visited[e.to] = true;
                    dist[e.to] = dist[v] + e.distance;
                    stack.push(e.to);
                }
            }
        }

        int longestNode = start;
        int longestDistance = 0;

        for(int i = 1; i <= n; i++) {
            if(dist[i] > longestDistance) {
                longestDistance = dist[i];
                longestNode = i;
            }
        }

        return new int[]{longestNode, longestDistance};
    }

    static class Edge {
        int to;
        int distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }
}
