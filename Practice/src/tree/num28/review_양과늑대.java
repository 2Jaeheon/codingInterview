package tree.num28;

import java.util.ArrayList;
import java.util.List;

public class review_양과늑대 {

    public static void main(String[] args) {
        int[] info = new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}; // 12개
        int[][] edges = new int[][]{ // 11개, 부모 자식 순
            {0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3},
            {6, 5}, {4, 6}, {8, 9}};
        System.out.println(solution(info, edges));
    }

    static List<Integer>[] tree;
    static int maxSheep = 0;

    public static int solution(int[] info, int[][] edges) {

        // info: 양 또는 늑대 정보 (0이 양, 1이 늑대)
        // edges: 부모 - 자식 pair
        int n = info.length;
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        // edges를 순회하며 부모에다가 자식을 추가
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }

        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0); // 루트에서 시작

        dfs(0, 0, 0, nextNodes, info);

        return maxSheep;
    }

    private static void dfs(int current, int sheep, int wolf, List<Integer> nextNodes, int[] info) {
        // 현재 노드 정보 반영
        if (info[current] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        // 조건 위반 시 중단
        if (wolf >= sheep) {
            return;
        }

        // 최대값 갱신
        maxSheep = Math.max(maxSheep, sheep);

        // 다음 이동 후보들 준비
        List<Integer> candidates = new ArrayList<>(nextNodes);
        candidates.remove(Integer.valueOf(current)); // 현재 노드 제거

        // 현재 노드의 자식 노드들 추가
        for (int child : tree[current]) {
            candidates.add(child);
        }

        // 후보 노드들 DFS 순회
        for (int next : candidates) {
            dfs(next, sheep, wolf, candidates, info);
        }
    }
}

