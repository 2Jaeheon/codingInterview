import java.util.*;
class Solution {
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

    public static int[][] solution(int[][] nodeInfo) {
        Node[] nodes = new Node[nodeInfo.length];

        // 각 노드에 좌표 및 번호를 저장함.
        for (int i = 0; i < nodeInfo.length; i++) {
            nodes[i] = new Node(i + 1, nodeInfo[i][0], nodeInfo[i][1]);
        }

        // y를 기준으로 내림차순 정렬
        Arrays.sort(nodes, (o1, o2) -> {
            if (o1.y == o2.y) {
                return Integer.compare(o1.x, o2.x);
            }
            return Integer.compare(o2.y, o1.y);
        });

        // 맨 처음 노드는 루트
        Node root = nodes[0];

        for (int i = 1; i < nodes.length; i++) {
            Node parent = root;

            while (true) {
                // 부모 노드의 x 좌료가 더 크면 왼쪽
                if (nodes[i].x < parent.x) {
                    if (parent.left == null) {
                        parent.left = nodes[i];
                        break;
                    } else {
                        parent = parent.left;
                    }
                } else { // 부모 노드의 x 좌표가 더 작거나 같으면 오른쪽
                    if (parent.right == null) {
                        parent.right = nodes[i];
                        break;
                    } else {
                        parent = parent.right;
                    }
                }
            }
        }

        // 순회 결과 저장용 리스트
        List<Integer> preorderList = new ArrayList<>();
        List<Integer> postorderList = new ArrayList<>();

        preorder(root, preorderList);
        postorder(root, postorderList);

        // 결과 변환
        int[][] result = new int[2][nodeInfo.length];
        for (int i = 0; i < nodeInfo.length; i++) {
            result[0][i] = preorderList.get(i);
            result[1][i] = postorderList.get(i);
        }

        return result;
    }

    // 전위 순회
    public static void preorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.num);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    // 후위 순회
    public static void postorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.num);
    }
}