package hash.num19;

import java.util.HashMap;
import java.util.Map;

public class 완주하지_못한_선수 {

    public static void main(String[] args) {
        String[] participant1 = {"leo", "kiki", "eden"};
        String[] participant2 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] participant3 = {"mislav", "stanko", "mislav", "ana"};

        String[] completion1 = {"eden", "kiki"};
        String[] completion2 = {"josipa", "filipa", "marina", "nikola"};
        String[] completion3 = {"stanko", "ana", "mislav"};

        System.out.println(solution(participant1, completion1));
        System.out.println(solution(participant2, completion2));
        System.out.println(solution(participant3, completion3));
    }

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

    public String solution2(String[] participant, String[] completion) {
        // ❶ 해시맵 생성
        HashMap<String, Integer> hashMap = new HashMap<>();
        // ❷ 완주한 선수들의 이름을 해시맵에 저장
        for (String string : completion) {
            hashMap.put(string, hashMap.getOrDefault(string, 0) + 1);
        }

        // ❸ 참가한 선수들의 이름을 키로 하는 값을 1씩 감소
        for (String string : participant) {
            // ❹ 완주하지 못한 선수를 찾으면 반환
            if (hashMap.getOrDefault(string, 0) == 0) {
                return string;
            }
            hashMap.put(string, hashMap.get(string) - 1);
        }

        return null;
    }

}
