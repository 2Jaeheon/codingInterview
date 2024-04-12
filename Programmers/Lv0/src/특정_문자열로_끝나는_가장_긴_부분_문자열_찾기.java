public class 특정_문자열로_끝나는_가장_긴_부분_문자열_찾기 {

    class Solution {

        public String solution(String myString, String pat) {
            String answer = "";

            for (int i = 0; i < myString.lastIndexOf(pat) + pat.length(); i++) {
                answer += myString.charAt(i);
            }
            return answer;
        }
    }
}
