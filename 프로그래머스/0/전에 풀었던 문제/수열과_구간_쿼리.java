public class 수열과_구간_쿼리 {

    public int[] solution(int[] arr, int[][] queries) {
        for (int i = 0; i < queries.length; i++) {
            int firstElement = queries[i][0];
            int secondElement = queries[i][1];
            for (int j = firstElement; j <= secondElement; j++) {
                arr[j]++;
            }
        }
        return arr;
    }
}
