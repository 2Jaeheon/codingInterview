package hash.num21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 오픈채팅방 {

    public static void main(String[] args) {
        String[] record = {
            "Enter uid1234 Muzi",
            "Enter uid4567 Prodo",
            "Leave uid1234",
            "Enter uid1234 Prodo",
            "Change uid4567 Ryan"};

        System.out.println(Arrays.toString(solution(record)));
    }

    public static String[] solution(String[] record) {
        //어떤 자료형에 저장? => 해쉬맵에 userid 와 이름을 따로 저장
        //record[1]은 Enter uid1234 Muzi
        //record[2]는 Enter uid4567 Prodo
        //record[3]은 Leave uid1234
        //record[4]는 Enter uid1234 Prodo
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


    private String[] solution2(String[] record) {
        // Enter/Leave 메세지를 저장할 해시맵 생성
        HashMap<String, String> msg = new HashMap<>();
        msg.put("Enter", "님이 들어왔습니다.");
        msg.put("Leave", "님이 나갔습니다.");

        HashMap<String, String> uid = new HashMap<>();

        // ❶ record의 각 줄을 하나씩 처리
        for (String s : record) {
            String[] cmd = s.split(" ");
            if (cmd.length == 3) { // ❷ Enter 또는 Change인 경우
                uid.put(cmd[1], cmd[2]);
            }
        }

        // 답을 저장할 answer List 생성
        ArrayList<String> answer = new ArrayList<>();

        // ❸ record의 각 줄을 하나씩 처리
        for (String s : record) {
            String[] cmd = s.split(" ");
            // ❹ 각 상태에 맞는 메세지를 answer에 저장
            if (msg.containsKey(cmd[0])) {
                answer.add(uid.get(cmd[1]) + msg.get(cmd[0]));
            }
        }

        return answer.toArray(new String[0]);
    }
}
