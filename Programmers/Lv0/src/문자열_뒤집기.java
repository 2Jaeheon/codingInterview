public class 문자열_뒤집기 {
    public String solution(String my_string, int s, int e) {
        String reverse = "";
        String answer = "";
        String temp = "";
        int k = s;
        for(int i = 0; i < e-s+1; i++){
            temp += my_string.charAt(k++);
        }

        for(int i = 0; i < s; i++){
            answer += my_string.charAt(i);
        }
        for(int i = temp.length() - 1; i >= 0; i--){
            answer += temp.charAt(i);
        }
        for(int i = e + 1; i < my_string.length(); i++){
            answer += my_string.charAt(i);
        }

        return answer;
    }


    //2
    public String solution(String my_string, int s, int e) {
        StringBuilder answer = new StringBuilder(my_string.substring(s, e + 1));
        answer.reverse();
        return my_string.substring(0, s) + answer + my_string.substring(e + 1);
    }


    //3
    public String solution(String my_string, int s, int e) {

        char[] spell = my_string.toCharArray();

        for (int i = s; i < e; i++, e--) {
            char tmp = spell[i];
            spell[i] = spell[e];
            spell[e] = tmp;
        }

        String answer = "";
        for (char c : spell) {
            answer += c;
        }
        return answer;
    }
}
