package graph.num36;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class 다익스트라 {

    public static void main(String[] args) {
        int[][] graph = new int[][]{{0, 1, 9}, {0, 2, 3}, {1, 0, 5}, {2, 1, 1}};
        int start = 0;
        int n = 3;

        System.out.println(Arrays.toString(solution(graph, start, n)));

        int[][] graph1 = new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {2, 3, 1}};
        int start1 = 0;
        int n1 = 4;

        System.out.println(Arrays.toString(solution(graph1, start1, n1)));
    }

    public static int[] solution(int[][] graph, int start, int n) {

        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : graph) {
            adjList.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        // 최소 비용을 저장할 배열
        int[] minCost = new int[n];
        // 방문 처리를 위한 배열
        boolean[] visited = new boolean[n];
        // 최소 비용 배열을 최대값으로 초기화
        Arrays.fill(minCost, Integer.MAX_VALUE);
        // 시작 노드의 최소 비용은 0
        minCost[start] = 0;

        // 우선순위 큐를 이용한 다익스트라 알고리즘
        // 우선순위 큐에는 int[] 배열을 저장하며, 0번째 인덱스는 노드 번호, 1번째 인덱스는 비용
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            // 현재 노드와 비용을 가져옴
            int[] current = pq.poll();
            int curNode = current[0];
            int curCost = current[1];

            if (visited[curNode]) {
                continue;
            }

            visited[curNode] = true;

            // 현재 노드와 연결된 노드들을 탐색
            for (int[] neighbor : adjList.get(curNode)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];

                // 현재 노드를 거쳐서 다음 노드로 가는 비용이 더 작다면 최소 비용 갱신
                if (minCost[nextNode] > curCost + weight) {
                    minCost[nextNode] = curCost + weight;
                    pq.add(new int[]{nextNode, minCost[nextNode]});
                }
            }
        }

        return minCost;
    }


    private static class Node {

        int dest, cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static int[] solution1(int[][] graph, int start, int n) {
        ArrayList<Node>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge : graph) {
            adjList[edge[0]].add(new Node(edge[1], edge[2]));
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            // 만약 현재 노드의 거리 값이 큐에서 가져온 거리값보다 크면, 해당 노드는 이미 방문한 것.
            // 큐에서 값을 꺼낼 때는 항상 현재까지 계산된 최단 거리값이 가장 작은 노드가 선택되기 때문
            // 근데 큐에서 꺼낸 현재 노드의 코스트가 기록된 최소 비용 배열의 코스트보다 작다면 그것은 무시해도 됨. 이미 방문한 것.
            if (dist[now.dest] < now.cost) {
                continue;
            }

            for (Node next : adjList[now.dest]) {
                if (dist[next.dest] > now.cost + next.cost) {
                    dist[next.dest] = now.cost + next.cost;
                    pq.add(new Node(next.dest, dist[next.dest]));
                }
            }
        }

        return dist;
    }
}
