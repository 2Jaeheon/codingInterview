package graph.num33;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class 섬_연결하기 {

    public static void main(String[] args) {
        int n = 4;
        int[][] costs = new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        int n1 = 5;
        int[][] costs1 = new int[][]{{0, 1, 1}, {1, 3, 1}, {1, 4, 2}, {2, 3, 2}};
        System.out.println(solution3(n, costs));
        //System.out.println(solution(n1, costs1));
    }

    /*

        public static int solution(int n, int[][] costs) {
            // 방문하지 않았고, 갈 수 있는 경로중 가장 작은 방향으로 가면 됨
            boolean[] visited = new boolean[n];
            int answer = 0;
            Arrays.fill(visited, false);

            List<int[]>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < costs.length; i++) {
                int origin = costs[i][0];
                int destination = costs[i][1];
                int weight = costs[i][2];

                graph[origin].add(new int[]{destination, weight});
                graph[destination].add(new int[]{origin, weight});
            }

            for (int i = 0; i < n; i++) {
                int min = graph[i].stream().mapToInt(x -> x[1]).min().getAsInt();

                System.out.println("min = " + min);
                if (getVisitedTrueNum(visited) == n - 1) {
                    break;
                }

                if (!visited[i]) {
                    visited[i] = true;
                    answer += min;
                }
            }

            printGraph(graph);
            return answer;
        }

        public static int getVisitedTrueNum(boolean[] visited) {
            int count = 0;
            for (boolean b : visited) {
                if (b) {
                    count++;
                }
            }
            return count;
        }

        // 그래프 출력 메서드
        public static void printGraph(List<int[]>[] graph) {
            for (int i = 0; i < graph.length; i++) {
                System.out.print("노드 " + i + ": ");
                for (int[] edge : graph[i]) {
                    System.out.print("[목적지: " + edge[0] + ", 가중치: " + edge[1] + "] ");
                }
                System.out.println();
            }
        }
    */
    public static int solution(int n, int[][] costs) {
        List<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.println("그래프 생성");
        generateGraph(costs, graph);

        System.out.println("그래프 출력");
        for (int i = 0; i < n; i++) {
            System.out.print("노드 " + i + ": ");
            for (Node node : graph[i]) {
                System.out.print("[목적지: " + node.destination + ", 가중치: " + node.weight + "] ");
            }
            System.out.println();
        }

        Stack<Node> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        visited[0] = true; // 시작 노드 방문 표시
        stack.addAll(graph[0]); // 시작 노드의 모든 연결 추가

        int answer = 0;

        while (!stack.isEmpty()) {
            // 스택에서 가장 작은 가중치를 가진 간선 선택
            Node current = stack.stream()
                .filter(node -> !visited[node.destination]) // 방문하지 않은 노드만 선택
                .min(Comparator.comparingInt(a -> a.weight))
                .orElse(null);

            if (current == null) {
                break; // 연결 가능한 노드가 없으면 종료
            }

            stack.remove(current); // 선택된 간선을 스택에서 제거
            answer += current.weight; // 가중치 추가
            visited[current.destination] = true; // 방문 표시

            // 현재 노드와 연결된 노드를 스택에 추가
            for (Node neighbor : graph[current.destination]) {
                if (!visited[neighbor.destination]) {
                    stack.push(neighbor);
                }
            }
        }

        return answer;
    }

    public static class Node {

        int destination;
        int weight;

        public Node(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static int solution2(int n, int[][] costs) {
        // 비용을 기준으로 다리를 오름차순 정렬
        Arrays.sort(costs, Comparator.comparingInt(o -> o[2]));

        // parent 배열 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int answer = 0;
        int edges = 0;

        for (int[] edge : costs) {
            // n - 1 개 다리가 연결된 경우 모든 섬이 연결됨
            if (edges == n - 1) {
                break;
            }

            // 현재 다리가 연결하는 두 섬이 이미 연결되어 있는지 확인
            if (find(edge[0]) != find(edge[1])) {
                // 두 섬을 하나의 집합으로 연결
                union(edge[0], edge[1]);
                // 현재 다리의 건설 비용을 총 비용에 추가
                answer += edge[2];
                // 사용된 다리의 수 1 증가
                edges++;
            }
        }

        return answer;
    }

    private static int[] parent;

    private static int find(int x) {
        // x 가 속한 집합의 루트 노드 찾기
        if (parent[x] == x) {
            return x;
        }
        // 경로 압축: x의 부모를 루트 노드로 설정
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        // 두 집합을 하나로  합치기
        int root1 = find(x);
        int root2 = find(y);
        parent[root2] = root1;
    }


    // Prim 알고리즘을 사용한 풀이
    public static int solution3(int n, int[][] costs) {
        List<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 생성
        generateGraph(costs, graph);

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

    private static void generateGraph(int[][] costs, List<Node>[] graph) {
        for (int[] cost : costs) {
            int from = cost[0];
            int to = cost[1];
            int weight = cost[2];
            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }
    }

}
