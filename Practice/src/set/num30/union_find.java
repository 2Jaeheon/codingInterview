package set.num30;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class union_find {

    public static void main(String[] args) {
        // 노드의 개수
        int k = 3;
        // 0 -> union(), 1 -> find()
        int[][] operation = new int[][]{{0, 0, 1}, {0, 1, 2}, {1, 1, 2}};
        System.out.println(Arrays.toString(solution(k, operation)));
    }

    public static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot > yRoot) {
            set[yRoot] = xRoot;
        } else {
            set[xRoot] = yRoot;
        }
    }

    public static int find(int a) {
        // set[x]는 집합, x는 부모를 찾고자 하는 대상
        if (set[a] == a) {
            return a;
        }

        set[a] = find(set[a]);
        return set[a];
    }

    public static int[] set;

    private static Boolean[] solution(int k, int[][] operation) {
        set = new int[k];

        for (int i = 0; i < set.length; i++) {
            set[i] = i;
        }

        List<Boolean> answers = new ArrayList<>();
        // 0 -> union(), 1 -> find()
        for (int[] x : operation) {
            if (x[0] == 0) {
                union(x[1], x[2]);
            } else if (x[0] == 1) {
                int a = find(x[1]);
                int b = find(x[2]);
                answers.add(a == b);
            }
        }

        return answers.toArray(Boolean[]::new);
    }
}
