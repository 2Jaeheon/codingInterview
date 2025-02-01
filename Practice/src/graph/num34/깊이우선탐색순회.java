package graph.num34;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class 깊이우선탐색순회 {

    public static void main(String[] args) {
        int[][] graph = new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        int start = 1;
        int n = 5;

        System.out.println(Arrays.toString(solution(graph, start, n)));
    }

    public static int[] solution(int[][] graph, int start, int n) {
        LinkedList<Integer>[] adjList = new LinkedList[n + 1];
        boolean[] visited = new boolean[n + 1];
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            adjList[i] = new LinkedList<>();
        }

        for (int[] g : graph) {
            int from = g[0];
            int to = g[1];
            adjList[from].add(to);
        }

/*
        for (List<Integer> list : adjList) {
            System.out.println(list);
        }
*/

        // 스택에 시작 노드 추가 및 방문 처리
        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited[current]) {
                visited[current] = true;
                result.add(current);

                for (int i = 0; i < adjList[current].size(); i++) {
                    int neighbor = adjList[current].get(i);
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        return result.stream().mapToInt(x -> x).toArray();
    }

    private static boolean[] visited;
    private static ArrayList<Integer> answer;
    private static ArrayList<Integer>[] adjList;

    public static int[] solution2(int[][] graph, int start, int n) {
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge : graph) {
            adjList[edge[0]].add(edge[1]);
        }

        visited = new boolean[n + 1];
        answer = new ArrayList<>();
        dfs(start);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void dfs(int current) {
        visited[current] = true;
        answer.add(current);
        for (int next : adjList[current]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}
