package graph.num40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class 배달 {

    public static void main(String[] args) {
        int[][] road = new int[][]{
            {1, 2, 1},
            {2, 3, 3},
            {5, 2, 2},
            {1, 4, 2},
            {5, 3, 1},
            {5, 4, 2}
        };
        int N = 5;
        int K = 3;
        System.out.println(solution(N, road, K));

        int[][] road2 = new int[][]{
            {1, 2, 1},
            {1, 3, 2},
            {2, 3, 2},
            {3, 4, 3},
            {3, 5, 2},
            {3, 5, 3},
            {5, 4, 2},
            {5, 6, 1}
        };
        int N2 = 6;
        int K2 = 4;
        System.out.println(solution(N2, road2, K2));
    }

    static class Node {

        int destination;
        int weight;

        public Node(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static int n;
    static int k;

    public static int solution(int N, int[][] road, int K) {
        n = N;
        k = K;
        int answer = 0;

        ArrayList<Node>[] adjList = new ArrayList[N + 1];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < road.length; i++) {
            int from = road[i][0];
            int to = road[i][1];
            int weight = road[i][2];

            // 양방향임
            adjList[from].add(new Node(to, weight));
            adjList[to].add(new Node(from, weight));
        }
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra(1, adjList, dist);

        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }

    private static void dijkstra(int start, List<Node>[] adjList, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.weight));
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.destination] < now.weight) {
                continue;
            }

            for (Node next : adjList[now.destination]) {
                int newDist = now.weight + next.weight;
                if (newDist < dist[next.destination]) {
                    dist[next.destination] = newDist;
                    pq.add(new Node(next.destination, newDist));
                }
            }
        }
    }
}
