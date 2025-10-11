import java.util.*;
import java.io.*;

public class Main{
    static class Node {
        int v, c; // vertex, cost
        Node(int v, int c) { this.v = v; this.c = c; }
    }
    
    public static void main(String args[]) throws Exception{
        // n개의 도시 (1_000)
        // m개의 버스 (100_000)
        // A -> B까지 가는데 버스비용을 최소화 하고싶음
        // 최소비용과 경로를 출력하라.

        // 다익스트라와 BFS?
        // 다익스트라로 최단 비용으로 가는 걸 검출해내고
        // 그 도시를 찾아내서...
        // 그래서 경로를 어떻게 출력하는데...?
        // 핵심은 최소 비용은 출력할 수 있는데
        // 그 사이의 도시 개수와, 경로 방문 도시 순서대로 출력해야 하는 것
        // 아 그러면 dijkstra에서 따로 처리를 할 수 있나???
        // dijkstra에서 따로 처리할 때마다 상태를 관리하는 방식으로 처리하면 가능하지 않을까??
        // 어떻게 그러면 상태를 관리하지?
        // 음... 포인터를 사용해서 prev[] 배열을 둬서 처리해보자.
        // dist[]가 갱신될 때 prev[]를 갱신시켜주면 될듯?
        // prev[i]: i에 갈 때 그 전의 어디에 있었는지를 나타내주는 배열
        
        // 버스 정보를 최적화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] head = new int[n + 1];
        Arrays.fill(head, -1);
        int[] to = new int[m];
        int[] w  = new int[m];
        int[] next = new int[m];
        int idx = 0;

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            to[idx] = t;
            w[idx]  = c;
            next[idx] = head[from];
            head[from] = idx++;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        // 이제 로직을 작성해야함.
        int[] dist = new int[n + 1];
        int[] prev = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        
        Arrays.fill(dist, 100_000_000);
        Arrays.fill(prev, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.c));
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.v;
            int d = cur.c;

            if (visited[u]) continue;
            if (d > dist[u]) continue;
            visited[u] = true;
            if (u == goal) break;

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                int nd = dist[u] + w[e];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    prev[v] = u;
                    pq.offer(new Node(v, nd));
                }
            }
        }
        
        // goal에서 prev를 따라서 역추적
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int v = goal; v != -1; v = prev[v]) stack.push(v);

        sb.append(dist[goal]).append('\n');
        sb.append(stack.size()).append('\n');
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            if (!stack.isEmpty()) sb.append(' ');
        }
        System.out.println(sb.toString());
    }
}
