import java.util.*;

class Solution {
    public static int[] solution(String[] enroll, String[] referral, String[] seller,
        int[] amount) {
        HashMap<String, String> parent = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            parent.put(enroll[i], referral[i]);
        }

        HashMap<String, Integer> total = new HashMap<>();

        for (int i = 0; i < seller.length; i++) {
            String curName = seller[i];

            int money = amount[i] * 100;

            while (money > 0 && !curName.equals("-")) {
                // 현재 판매자가 받을 금액 계산 (10 퍼센트를 제외한 금
                total.put(curName, total.getOrDefault(curName, 0) + money - (money / 10));
                curName = parent.get(curName);
                money /= 10;
            }
        }

        int[] answer = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) {
            answer[i] = total.getOrDefault(enroll[i], 0);
        }

        return answer;
    }
}