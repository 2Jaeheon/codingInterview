import java.util.*;

public class n제곱_배열_자르기 {


    class Solution {

        public int[] solution(int n, long left, long right) {
            int[] answer = new int[(int) (right - left) + 1];
            int k = 0;

            for (long i = left; i <= right; i++) {
                long row = (long) i / (long) n;
                long col = (long) i % (long) n;
                answer[k++] = (int) Math.max(row, col) + 1;
            }
            return answer;
        }
    }
}
