package hash.num23;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class 신고_결과_받기 {

    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;

        String[] id_list2 = {"con", "ryan"};
        String[] report2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k2 = 3;

        System.out.println(Arrays.toString(solution(id_list, report, k)));
        System.out.println(Arrays.toString(solution(id_list2, report2, k2)));
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        //한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.
        //Which Data Structure????
        //어떤 데이터를 관리해야할까?
        //신고 횟수, 누가 누구를 신고했는지
        //신고당한 해시 맵, 특정 유저가 어떤 유저를 신고했는지를 저장하는 해시맵
        int[] answer = new int[id_list.length];
        LinkedHashMap<String, Integer> answerMap = new LinkedHashMap<>();
        HashMap<String, Integer> reportedMap = new HashMap<>();
        HashMap<String, HashSet<String>> uniqueReportsMap = new HashMap<>();

        //초기화
        for (String id : id_list) {
            answerMap.put(id, 0);
            uniqueReportsMap.put(id, new HashSet<>());
        }

        // 신고 내용을 처리
        for (String str : report) {
            String[] person = str.split(" ");
            String perpetrator = person[0];
            String victim = person[1];

            // 동일한 사용자가 동일한 유저를 여러 번 신고하는 경우 1회로 처리
            if (!uniqueReportsMap.get(victim).contains(perpetrator)) {
                uniqueReportsMap.get(victim).add(perpetrator);
                reportedMap.put(victim, reportedMap.getOrDefault(victim, 0) + 1);
            }
        }

        // 신고 횟수가 k번 이상인 경우 신고한 유저에게 메일 발송 횟수를 기록
        for (String victim : uniqueReportsMap.keySet()) {
            if (reportedMap.getOrDefault(victim, 0) >= k) {
                for (String perpetrator : uniqueReportsMap.get(victim)) {
                    answerMap.put(perpetrator, answerMap.get(perpetrator) + 1);
                }
            }
        }

        return answerMap
            .values()
            .stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }

    public int[] solution2(String[] id_list, String[] report, int k) {
        // 신고당한 유저 - 신고 유저 집합을 저장할 해시맵
        HashMap<String, HashSet<String>> reportedUser = new HashMap<>();
        // 처리 결과 메일을 받은 유저 - 받은 횟수를 저장할 해시맵
        HashMap<String, Integer> count = new HashMap<>();

        // ❶ 신고 기록 순회
        for (String r : report) {
            String[] s = r.split(" ");
            String userId = s[0];
            String reportedId = s[1];

            if (!reportedUser.containsKey(reportedId)) { // ❷ 신고당한 기록이 없다면
                reportedUser.put(reportedId, new HashSet<>());
            }
            // ❸ 신고한 사람의 아이디를 해시맵의 value인 해시셋에 추가
            reportedUser.get(reportedId).add(userId);
        }

        for (Map.Entry<String, HashSet<String>> entry : reportedUser.entrySet()) {
            if (entry.getValue().size() >= k) { // ❹ 정지 기준에 만족하는지 확인
                for (String uid : entry.getValue()) { // ❺ 해시셋을 순회하며 count 계산
                    count.put(uid, count.getOrDefault(uid, 0) + 1);
                }
            }
        }

        int[] answer = new int[id_list.length];

        // ❻ 각 아이디별 메일을 받은 횟수를 순서대로 정리
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = count.getOrDefault(id_list[i], 0);
        }

        return answer;
    }
}
