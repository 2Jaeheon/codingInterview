package hash.num20;

import java.util.HashMap;
import java.util.Map;

public class 할인_행사 {

    public static void main(String[] args) {
        String[] want1 = {"banana", "apple", "rice", "pork", "pot"};
        int[] number1 = {3, 2, 2, 2, 1};
        String[] discount1 = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork",
            "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

        System.out.println(solution2(want1, number1, discount1));
    }

    public static int solution(String[] want, int[] number, String[] discount) {
        //자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우를 찾아야함.
        //첫 번째 날로부터 10일 이내에 pot이 없으므로 구매 X
        //두 번째 날로부터 10일 이내에 banana 3개가 없음.
        //세 번째 날과 네 번째, 다섯 번째 날이 일치함 => 3을 리턴
        int answer = 0;

        for (int i = 0; i + 10 < discount.length + 1; i++) {
            //맵에 원하는 품목과 원하는 개수를 각각 저장
            Map<String, Integer> hashMap = new HashMap<>();
            for (int j = 0; j < want.length; j++) {
                hashMap.put(want[j], number[j]);
            }

            for (int j = i; j < 10 + i; j++) {
                if (hashMap.containsKey(discount[j])) {
                    hashMap.put(discount[j], hashMap.get(discount[j]) - 1);
                }

            }

            int zeroCount = 0;
            for (int value : hashMap.values()) {
                if (value == 0) {
                    zeroCount++;
                }
            }

            if (zeroCount == hashMap.size()) {
                answer++;
            }
        }
        return answer;
    }

    public static int solution2(String[] want, int[] number, String[] discount) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }

        int answer = 0;

        for (int i = 0; i < discount.length - 9; i++) {
            HashMap<String, Integer> discountMap = new HashMap<>();

            for (int j = i; j < i + 10; j++) {
                if (map.containsKey(discount[j])) {
                    discountMap.put(discount[j], discountMap.getOrDefault(discount[j], 0) + 1);
                }
            }

            if (discountMap.equals(map)) {
                answer++;
            }
        }
        return answer;
    }
}
