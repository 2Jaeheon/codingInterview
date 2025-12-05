import java.util.*;

class Edge {
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

public class Main{
    static List<List<Edge>> tree = new ArrayList<>();
    static boolean[] visited;
    static int maxDist = 0;
    static int maxNode = 0;
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();

        for(int i = 0; i < v + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for(int i = 0; i < v; i++) {
            int from = sc.nextInt();
            int to = 0;
            int cost = 0;
            
            while(true) {
                to = sc.nextInt();
                if(to == -1) {
                    break;
                }
                cost = sc.nextInt();

                tree.get(from).add(new Edge(to, cost));
            }
        }

        
        visited = new boolean[v + 1];
        dfs(1, 0);
        
        visited = new boolean[v + 1];
        int startNode = maxNode;
        maxDist = 0;
        
        dfs(startNode, 0);
        System.out.println(maxDist);
    }
    
    public static void dfs(int currentNode, int currentDist) {
        // 방문 체크
        visited[currentNode] = true;
        
        // 현재 거리가 maxDist보다 크면 갱신
        if (currentDist > maxDist) {
            maxDist = currentDist;
            maxNode = currentNode;
        }
        
        // 연결된 노드들 탐색
        for (Edge edge : tree.get(currentNode)) {
            int nextNode = edge.to;
            int distToNext = edge.cost;
    
            if (!visited[nextNode]) {
                dfs(nextNode, currentDist + distToNext);
            }
        }
    }
}
