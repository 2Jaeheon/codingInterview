import java.util.*;
class Solution {
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
}