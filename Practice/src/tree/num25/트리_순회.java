package tree.num25;

import java.util.Arrays;

public class 트리_순회 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(solution(arr)));
    }

    public static String[] solution(int[] arr) {
        String[] result = new String[3];
        result[0] = preOrder(arr, 0);
        result[1] = inOrder(arr, 0);
        result[2] = postOrder(arr, 0);

        return result;
    }

    public static String preOrder(int[] arr, int index) {
        if (index >= arr.length) {
            return "";
        }

        return arr[index] + preOrder(arr, index * 2 + 1) + preOrder(arr, index * 2 + 2);
    }

    public static String inOrder(int[] arr, int index) {
        if (index >= arr.length) {
            return "";
        }

        return inOrder(arr, index * 2 + 1) + arr[index] + inOrder(arr, index * 2 + 2);
    }

    public static String postOrder(int[] arr, int index) {
        if (index >= arr.length) {
            return "";
        }

        return postOrder(arr, index * 2 + 1) + inOrder(arr, index * 2 + 2) + arr[index];
    }
}
