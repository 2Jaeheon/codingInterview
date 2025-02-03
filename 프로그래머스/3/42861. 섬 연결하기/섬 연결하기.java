import java.util.*;

class Solution {
    public static int solution(int n, int[][] costs) {
        List<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 생성
        for (int[] cost : costs) {
            int from = cost[0];
            int to = cost[1];
            int weight = cost[2];
            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }

        // 우선순위 큐 (가중치가 작은 간선 우선)
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        boolean[] visited = new boolean[n];
        visited[0] = true; // 시작 노드 방문

        // 시작 노드(0)와 연결된 간선을 모두 추가
        pq.addAll(graph[0]);

        int answer = 0;
        int edgeCount = 0; // 추가된 간선 개수

        while (!pq.isEmpty() && edgeCount < n - 1) {
            Node current = pq.poll(); // 가장 가중치가 작은 간선 선택

            if (visited[current.destination]) {
                continue; // 이미 방문한 노드는 무시
            }

            // 최소 신장 트리에 추가
            answer += current.weight;
            visited[current.destination] = true;
            edgeCount++; // 연결된 간선 개수 증가

            // 현재 노드와 연결된 간선들 추가
            for (Node neighbor : graph[current.destination]) {
                if (!visited[neighbor.destination]) {
                    pq.add(neighbor);
                }
            }
        }

        return answer;
    }

    static class Node {
        int destination;
        int weight;

        public Node(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}