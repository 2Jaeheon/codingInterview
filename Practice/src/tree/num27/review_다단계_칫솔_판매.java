package tree.num27;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.w3c.dom.Node;

public class review_다단계_칫솔_판매 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(
            new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
            new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
            new String[]{"young", "john", "tod", "emily", "mary"},
            new int[]{12, 4, 2, 5, 10})));
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller,
        int[] amount) {
        HashMap<String, String> parent = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            parent.put(enroll[i], referral[i]);
        }

        HashMap<String, Integer> total = new HashMap<>();
        // total에는 앞으로 각 비용들을 저장하도록 할거임.
        // parent에는 각 자식들에서 부모로 이어지도록 함.

        for (int i = 0; i < seller.length; i++) {
            String curName = seller[i];

            int money = amount[i] * 100;

            while (money > 0 && !curName.equals("-")) {
                total.put(curName, total.getOrDefault(curName, 0) + money - (money / 10));
                //curName은 부모에서 get 해오면 됨. 그러면 다음 걸로 전환되는 거임.
                curName = parent.get(curName);
                money /= 10;
            }
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = total.getOrDefault(enroll[i], 0);
        }

        return answer;
    }

}
