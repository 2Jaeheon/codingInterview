import java.util.*;
class Solution {
    public int solution(String[] strArr) {
        int answer = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i=0;i<strArr.length;i++){
            if(!temp.contains(strArr[i].length())){
                temp.add(strArr[i].length());
            }
        }
        
        int[] temp2 = new int[temp.size()];
        for(int i=0;i<strArr.length;i++){
            temp2[strArr[i].length()-1]++;
        }
        
        for(int i=0;i<temp2.length;i++){
            if(answer<temp2[i]){
                answer = temp2[i];
            }
        }
        return answer;
    }
}