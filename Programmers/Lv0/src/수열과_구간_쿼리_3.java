public class 수열과_구간_쿼리_3 {

    class Solution {

        public int[] solution(int[] arr, int[][] queries) {
            for (int i = 0; i < queries.length; i++) {
                int x = queries[i][0];
                int y = queries[i][1];
                swap(arr, x, y);
            }
            return arr;
        }

        //i번째 원소와 j번째원소를 교환
        public static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
