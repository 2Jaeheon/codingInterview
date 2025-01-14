package tree.num25;

import java.util.Arrays;

public class 트리_순회 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 2, 3, 4, 5, 6, 7})));
    }

    public static String[] solution(int[] nodes) {
        String preOrderString = preOrder(nodes, 0);
        String inOrderString = inOrder(nodes, 0);
        String postOrderString = postOrder(nodes, 0);

        return new String[]{preOrderString, inOrderString, postOrderString};
    }


    public static String preOrder(int[] nodes, int index) {
        if (index >= nodes.length) {
            return "";
        }

        return nodes[index] + preOrder(nodes, 2 * index + 1) + preOrder(nodes, 2 * index + 2);
    }

    public static String inOrder(int[] nodes, int index) {
        if (index >= nodes.length) {
            return "";
        }

        return inOrder(nodes, 2 * index + 1) + nodes[index] + inOrder(nodes, 2 * index + 2);
    }

    public static String postOrder(int[] nodes, int index) {
        if (index >= nodes.length) {
            return "";
        }

        return postOrder(nodes, 2 * index + 1) + postOrder(nodes, 2 * index + 2) + nodes[index];
    }
}
