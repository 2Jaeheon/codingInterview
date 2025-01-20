package tree.num29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 길_찾기_게임 {

    public static void main(String[] args) {
        int[][] nodeinfo = new int[][]{
            {5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        System.out.println(Arrays.deepToString(solution(nodeinfo)));
    }

    public static ArrayList<Node>[] tree;

    public static int[][] solution(int[][] nodeinfo) {
        Node root = makeBT(nodeinfo);
        ArrayList<Integer> preOrderList = new ArrayList<>();
        preOrder(root, preOrderList);
        ArrayList<Integer> postOrderList = new ArrayList<>();
        postOrder(root, postOrderList);

        int[][] answer = new int[2][nodeinfo.length];
        answer[0] = preOrderList.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postOrderList.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    public static class Node {

        int num;
        int x;
        int y;
        Node left, right;

        public Node(int num, int x, int y) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static Node makeBT(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];

        // 각 노드에 좌표랑 번호를 저장
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        // y 기준으로 내림차순으로 정렬, y가 같다면 x를 기준으로 오름차순 정렬
        Arrays.sort(nodes, (o1, o2) -> {
            if (o1.y == o2.y) {
                return Integer.compare(o1.x, o2.x);
            }
            return Integer.compare(o2.y, o1.y);
        });

        // 맨 처음 노드는 무조건 루트노드
        Node root = nodes[0];

        for (int i = 1; i < nodes.length; i++) {
            Node parent = root;
            while (true) {
                // 부모 노드의 x좌표가 더 크면 왼쪽으로
                if (nodes[i].x < parent.x) {
                    if (parent.left == null) {
                        parent.left = nodes[i];
                        break;
                    } else {
                        parent = parent.left;
                    }

                } else { // 부모노드의 x좌료가 더 작거나 같으면 오른쪽으로
                    if (parent.right == null) {
                        parent.right = nodes[i];
                        break;
                    } else {
                        parent = parent.right;
                    }
                }
            }
        }
        return nodes[0];
    }

    private static void preOrder(Node curr, ArrayList<Integer> answer) {
        if (curr == null) {
            return;
        }

        answer.add(curr.num);
        preOrder(curr.left, answer);
        preOrder(curr.right, answer);
    }

    private static void postOrder(Node curr, ArrayList<Integer> answer) {
        if (curr == null) {
            return;
        }
        postOrder(curr.left, answer);
        postOrder(curr.right, answer);
        answer.add(curr.num);
    }
}
