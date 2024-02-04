public class 수_조작하기 {
    public String solution(int[] numLog) {
        String answer = "";
        int next = 0;
        int now = 0;

        for(int i = 0; i < numLog.length - 1; i++){
            now = numLog[i];
            next = numLog[i+1];
            if(next == now + 1){
                answer += "w";
            }
            else if(next == now + 10){
                answer += "d";
            }
            else if(next == now - 1){
                answer += "s";
            }
            else if(next == now - 10){
                answer += "a";
            }
        }
        return answer;
    }
}
