import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int pair = sc.nextInt();
        boolean[] visited = new boolean[n+1];
        int count = 0;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            List<Integer> list = new LinkedList<>();
            graph.put(i, list);
        }
        
        for(int i = 0; i < pair; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        visited[1] = true;
        while(!queue.isEmpty()) {
            int x = queue.poll();

            for(int i : graph.get(x)) {
                if(!visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}