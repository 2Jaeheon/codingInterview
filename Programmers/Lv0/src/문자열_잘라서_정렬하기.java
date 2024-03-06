public class 문자열_잘라서_정렬하기 {

    class Solution {

        public String[] solution(String myString) {
            String[] temp = myString.split("x");
            Arrays.sort(temp);
            int count = 0;
            int k = 0;
            for (int i = 0; i < temp.length; i++) {
                if (!temp[i].isEmpty()) {
                    count++;
                }
            }
            String[] answer = new String[count];

            for (int i = 0; i < temp.length; i++) {
                if (!temp[i].isEmpty()) {
                    answer[k++] = temp[i];
                }
            }
            return answer;

        }
    }
