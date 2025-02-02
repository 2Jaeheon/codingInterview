import java.util.*;
class Solution {
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

        //printAdjlist(adjList);

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
}