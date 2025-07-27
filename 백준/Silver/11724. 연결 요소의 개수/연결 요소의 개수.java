import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        boolean[] visited = new boolean[n + 1];
        List<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        // for(List<Integer> list : graph) {
        //     System.out.println(list);
        // }

        
        int count = 0;

        // 모든 숫자를 하나씩 순회하면서 있는지를 체크
        for(int i = 1; i <= n; i++) {

            // 만약 방문하지 않은 것이라면 count를 하나 추가
            if(!visited[i]) {
                count++;

                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                visited[i] = true;

                // 여기서 BFS를 실행
                while(!queue.isEmpty()) {
                    int cur = queue.poll();
        
                    for(int next : graph.get(cur)) {
                        if(!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }
                }
            }
            
        }
        System.out.println(count);
    }
}