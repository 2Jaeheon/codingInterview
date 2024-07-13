public class 글자_지우기 {
    public String solution(String my_string, int[] indices) {
        String answer = "";
        int max = indices[0];
        int k = 0;
        for(int i = 0; i < indices.length; i++){
            if(indices[i] > max) {
                max = indices[i];
            }
        }
        int[] temp = new int[my_string.length()];

        for(int i = 0; i < indices.length; i++){
            temp[indices[i]] = 1;
        }

        for(int i = 0; i < temp.length; i++){
            if(temp[i] != 1){
                answer += my_string.charAt(i);
            }
        }

        return answer;
    }
}
