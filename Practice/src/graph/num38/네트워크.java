package graph.num38;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class 네트워크 {

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = new int[][]{
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        System.out.println(solution2(n, computers));
    }

    public static int solution(int n, int[][] computers) {
        int[] visited = new int[n];
        ArrayList<Integer>[] adjList = new ArrayList[n];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers[0].length; j++) {
                if (i != j && computers[i][j] == 1) {
                    adjList[i].add(j);
                }
            }
        }

        printAdjlist(adjList);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                count++;
                stack.push(i);

                while (!stack.isEmpty()) {
                    int current = stack.pop();
                    visited[current] = 1;
                    for (int neighbor : adjList[current]) {
                        if (visited[neighbor] == 0) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }

        return count;
    }

    private static void printAdjlist(ArrayList<Integer>[] adjList) {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + " -> ");
            for (int neighbor : adjList[i]) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public static int solution2(int n, int[][] computers) {
        int[] parent = new int[n];

        // 1. 부모 배열 초기화 (각 노드는 자기 자신이 부모)
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 2. 유니온 연산 수행
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }

        // 3. 고유한 루트 노드의 개수 찾기
        HashSet<Integer> uniqueRoots = new HashSet<>();

        for (int i = 0; i < n; i++) {
            uniqueRoots.add(find(parent, i));
        }

        return uniqueRoots.size();
    }

    // 부모 노드를 찾는 함수 (경로 압축 기법 적용)
    private static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent, parent[x]);  // 경로 압축
    }

    // 두 노드를 하나의 집합으로 합치는 함수
    private static void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);

        if (rootA != rootB) {
            parent[rootB] = rootA; // 하나의 루트로 통합
        }
    }
}
