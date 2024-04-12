class Solution {
    public int solution(String myString, String pat) {
        int answer = 0;
        String str = "";
        for(int i = 0; i < myString.length(); i++){
            str = myString.substring(0, i+1);
            if(str.endsWith(pat)){
                answer++;
            }
        }
        
        return answer;
    }
}