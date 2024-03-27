public class _1로_만들기 {

    class Solution {

        public int solution(int[] num_list) {
            int answer = 0;
            int temp;
            for (int i = 0; i < num_list.length; i++) {
                temp = num_list[i];
                for (int j = 0; temp != 1; j++) {
                    if (temp % 2 == 0) {
                        temp /= 2;
                        answer++;
                    } else {
                        temp = (temp - 1) / 2;
                        answer++;
                    }
                }
            }
            return answer;
        }
    }
}
