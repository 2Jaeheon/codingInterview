import java.util.*;
class Solution {
        public static int solution(int n, int[][] costs) {
        List<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.println("그래프 생성");
        for (int[] cost : costs) {
            int from = cost[0];
            int to = cost[1];
            int weight = cost[2];
            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }

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
                .min((a, b) -> Integer.compare(a.weight, b.weight))
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
}