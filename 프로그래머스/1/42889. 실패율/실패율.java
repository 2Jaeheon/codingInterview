import java.util.*;

class Solution {
     public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int len = stages.length;
        int count = 0;
        HashMap<Integer, Float> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            count = 0;
            int stageNum = i + 1;

            for (int j = 0; j < stages.length; j++) {
                if (stageNum == stages[j]) {
                    count++;
                }
            }

            if (len == 0) {
                map.put(stageNum, 0f);
            } else {
                float failureRate = ((float) count / len);
                map.put(stageNum, failureRate);
                len -= count;
            }
        }

        answer = map.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) 
            .mapToInt(Map.Entry::getKey) 
            .toArray(); 

        return answer;
    }
}