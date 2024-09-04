import java.util.*;
class Solution {
     public static String solution(String[] participant, String[] completion) {
        //참가자 이름과 중복횟수
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < participant.length; i++) {
            String player = participant[i];
            if (map.containsKey(player)) {
                map.put(participant[i], map.get(player) + 1);
            } else {
                map.put(player, 1);
            }
        }

        for (int i = 0; i < completion.length; i++) {
            if (map.containsKey(completion[i])) {
                map.put(completion[i], map.get(completion[i]) - 1);
            }
        }

        for (String string : map.keySet()) {
            if (map.get(string) == 1) {
                return string;
            }
        }
        return "";
    }
}