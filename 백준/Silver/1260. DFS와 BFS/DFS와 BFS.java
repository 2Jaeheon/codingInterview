import java.util.*;

public class Main {
    public static List<ArrayList<Integer>> lists = new ArrayList<>();
    public static boolean[] visited;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int v = sc.nextInt();

        // DFS & BFS 해야함.
        // m개 개만큼 간선을 알려줌
        // 리스트구조로 하는게 좋아보임
        
        for(int i = 0; i <= n; i++) {
            lists.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            lists.get(x).add(y);
            lists.get(y).add(x);
        }

        // 정렬 해야 제대로 나옴.
        for(int i = 1; i <= n; i++) {
            Collections.sort(lists.get(i));
        }

        // DFS
        visited = new boolean[n + 1];
        dfs(v);

        // 줄바꿈
        System.out.println();
        
        // BFS
        visited = new boolean[n + 1];
        bfs(v);
        
    }

    // dfs 재귀로 풀어야 정답으로 나오네;
    public static void dfs(int v) {
        visited[v] = true;
        System.out.print(v + " ");

        // 리스트에서 꺼낸 것들중에서 방문하지 않았다면 다시 dfs 호출
        for(int next : lists.get(v)) {
            if(!visited[next]) {
                dfs(next);
            }
        }
    }

    public static void bfs(int v) {
        Queue<Integer> queue = new ArrayDeque<>();
        visited[v] = true;
        queue.offer(v);
        
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.print(curr + " ");
            
            for(int next : lists.get(curr)) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}