package Array;

import java.util.Arrays;

public class 행렬의_곱셈 {

    public static void main(String[] args) {
        int[][] arr1 = {
            {1, 4},
            {3, 2},
            {4, 1}
        };

        int[][] arr2 = {
            {3, 3},
            {3, 3}
        };

        int[][] answer = new int[arr1.length][arr2.length];
        answer = solution(arr1, arr2);

        System.out.println(Arrays.deepToString(answer));
    }

    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];

        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[0].length; j++) {
                for (int k = 0; k < answer[0].length; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return answer;
    }
}
