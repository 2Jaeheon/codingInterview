import java.util.*;
class Solution {
    public static String[] solution(String[] record) {
        Map<String, String> map = new java.util.HashMap<>();
        int answerSize = 0;
        int idx = 0;
        for (String str : record) {
            if (str.contains("Enter") || str.contains("Leave")) {
                answerSize++;
            }
        }
        String[] answer = new String[answerSize];

        for (int i = 0; i < record.length; i++) {
            String[] split = record[i].split(" ");
            if (split[0].equals("Enter") || split[0].equals("Change")) {
                map.put(split[1], split[2]);
            }
        }

        for (int i = 0; i < record.length; i++) {
            String[] split = record[i].split(" ");
            if (split[0].equals("Enter")) {
                answer[idx++] = map.get(split[1]) + "님이 들어왔습니다.";
            } else if (split[0].equals("Leave")) {
                answer[idx++] = map.get(split[1]) + "님이 나갔습니다.";
            }
        }
        return answer;
    }
}