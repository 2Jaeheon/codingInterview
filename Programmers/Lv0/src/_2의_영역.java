public class _2의_영역 {

    class Solution {

        public int[] solution(int[] arr) {
            int start = 0;
            int end = 0;
            boolean flag = false;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 2) {
                    start = i;
                    flag = true;
                    break;
                }
            }
            for (int j = arr.length - 1; j >= 0; j--) {
                if (arr[j] == 2) {
                    end = j;
                    flag = true;
                    break;
                }
            }

            int[] answer = new int[end - start + 1];
            if (flag == false) {
                answer[0] = -1;
                return answer;
            }

            for (int i = 0, j = start; i < end - start + 1; i++) {
                answer[i] = arr[j++];
            }

            return answer;
        }
    }
}
