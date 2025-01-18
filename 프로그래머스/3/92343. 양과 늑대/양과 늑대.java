import java.util.*;
class Solution {
    static class Info {

        int node, sheep, wolf;
        HashSet<Integer> visited;

        public Info(int node, int sheep, int wolf, HashSet<Integer> visited) {
            this.node = node;
            this.sheep = sheep;
            this.wolf = wolf;
            this.visited = visited;
        }
    }

    public static int solution(int[] info, int[][] edges) {
        // 현재 위치, 양의 수, 늑대의 수, 방문한 노드 저장을 위한 클래스
        buildTree(info, edges);
        int answer = 0;

        // BFS를 위한 큐 생성 및 초기 상태 설정
        ArrayDeque<Info> queue = new ArrayDeque<>();
        queue.add(new Info(0, 1, 0, new HashSet<>()));

        // BFS start
        while (!queue.isEmpty()) {
            Info now = queue.poll();

            // 최대 양의 수 업데이트
            answer = Math.max(answer, now.sheep);
            // 방문한 노드 집합에 현재 노드의 이웃 노드 추가
            now.visited.addAll(tree[now.node]);

            for (int next : now.visited) {
                HashSet<Integer> set = new HashSet<>(now.visited);
                set.remove(next);

                if (info[next] == 1) {
                    // 방문하는 순간에는 양이 늑대보다 많은지를 확인해야함.
                    if (now.sheep > now.wolf + 1) {
                        queue.add(new Info(next, now.sheep, now.wolf + 1, set));
                    }
                } else {
                    queue.add(new Info(next, now.sheep + 1, now.wolf, set));
                }
            }

        }
        return answer;
    }

    // 트리 정보를 저장할 인접 리스트
    private static ArrayList<Integer>[] tree;

    private static void buildTree(int[] info, int[][] edges) {
        tree = new ArrayList[info.length];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }
    }
}