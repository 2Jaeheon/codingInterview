package set.num35;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 너비우선탐색순회 {

    public static void main(String[] args) {
        int[][] graph = new int[][]{
            {1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}, {4, 8}, {5, 8},
            {6, 9}, {7, 9}};
        int start = 1;
        int n = 9;
        System.out.println(Arrays.toString(solution(graph, start, n)));
        int[][] graph1 = new int[][]{{1, 3}, {3, 4}, {3, 5}, {5, 2}};
        int start1 = 1;
        int n1 = 5;
        System.out.println(Arrays.toString(solution(graph1, start1, n1)));
    }

    public static int[] solution(int[][] graph, int start, int n) {
        List<Integer>[] list = new ArrayList[n + 1];

        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }

        for (int[] edge : graph) {
            list[edge[0]].add(edge[1]);
        }

        // list 출력
        /*for (List<Integer> l : list) {
            System.out.println(l);
        }
*/
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        List<Integer> result = new ArrayList<>();

        // 시작 노드 방문 처리 및 큐에 추가
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            // 방문하지 않은 이웃 노드를 큐에 추가하면서 방문 처리
            for (int neighbor : list[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true; // 큐에 추가하기 전에 방문 처리
                    queue.offer(neighbor);
                }
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static ArrayList<Integer>[] adjList;
    private static boolean[] visited;
    private static ArrayList<Integer> answer;

    private static int[] solution1(int[][] graph, int start, int n) {
        adjList = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        answer = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] g : graph) {
            int from = g[0];
            int to = g[1];
            adjList[from].add(to);
        }

        bfs(start);

        return answer.stream().mapToInt(x -> x).toArray();
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            answer.add(current);

            for (int next : adjList[current]) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }
}