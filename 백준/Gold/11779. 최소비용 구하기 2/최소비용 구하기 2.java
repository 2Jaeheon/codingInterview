import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int v, cost;
        Node(int v, int cost) { this.v = v; this.cost = cost; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] head = new int[n + 1];
        Arrays.fill(head, -1);
        int[] to = new int[m];
        int[] w = new int[m];
        int[] next = new int[m];
        int idx = 0;

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            to[idx] = t;
            w[idx] = c;
            next[idx] = head[from];
            head[from] = idx++;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        final int INF = 1_000_000_000;
        int[] dist = new int[n + 1];
        int[] prev = new int[n + 1];
        boolean[] vis = new boolean[n + 1];
        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.v;
            if (vis[u]) continue;
            vis[u] = true;
            if (u == goal) break;

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                int nc = dist[u] + w[e];
                if (nc < dist[v]) {
                    dist[v] = nc;
                    prev[v] = u;
                    pq.offer(new Node(v, nc));
                }
            }
        }

        // 경로 복원
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