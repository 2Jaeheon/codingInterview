package tree.num28;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class 양과_늑대 {

    public static void main(String[] args) {
        int[] info = new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}; // 12개
        int[][] edges = new int[][]{ // 11개, 부모 자식 순
            {0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3},
            {6, 5}, {4, 6}, {8, 9}};
        System.out.println(solution2(info, edges));
    }
/*
stack과 DFS로 해결하려고 하였으나, 복잡해서 풀이에 실패.

    public static int solution(int[] info, int[][] edges) {
        // 노드 번호 알아야함. 노드 번호가 구분자.
        // 자식과 부모가 누구인지 알아야함.
        // 양인지 늑대인지 알아야함.
        // 부모는 무조건 하나 (binary tree)

        // 찾는 방법: 스택을 이용
        // 다음으로 이동할 때 스택에 push()
        // 종료조건이라면 stack에서 pop();

        // 초기 자료구조 설정
        // 0은 양, 1은 늑대
        Animal[] animals = new Animal[info.length];
        for (int i = 0; i < animals.length; i++) {
            animals[i] = new Animal(-1, -1, -1);
        }

        for (int i = 0, animalIndex = 1; i < edges.length; i++) {
            int animalInfo = info[i];
            int parent = edges[i][0];
            int child = edges[i][1];

            animals[animalIndex++].info = animalInfo;
            animals[child].parent = parent;
            animals[parent].child.add(child);
        }

        // 탐색
        int curNode = 0;
        int answer = 0;

        ArrayDeque<Animal> stack = new ArrayDeque<>();
        stack.push(animals[curNode]);
        animals[0].isVisit = true;

        // 다음으로 이동할 때 스택에 push()
        // 종료조건이라면 stack에서 pop();
        int index = 0;
        while (!stack.isEmpty()) {
            // 자식 노드를 방문했는지 확인하고 자식 노드중 방문하지 않은 노드의 번호를 체크
            if (animals[animals[curNode].child.get(0)].isVisit == true) {
                curNode = animals[curNode].child.get(1);
            } else {
                curNode = animals[curNode].child.get(0);
            }
            // stack 에 현재 방문중인 노드 삽입
            stack.push(animals[curNode]);


        }

        return answer;
    }

    static class Animal {

        int parent;
        List<Integer> child;
        int info;
        boolean isVisit;

        public Animal(int parent, int child, int info) {
            this.parent = parent;
            this.child = new ArrayList<>();
            this.info = info;
            this.isVisit = false;
        }
    }
*/


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

    public static int solution2(int[] info, int[][] edges) {
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
