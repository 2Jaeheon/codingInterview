import java.util.*;
class Solution {
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

            if(zeroCount == hashMap.size()){
                answer++;
            }
        }
        return answer;
    }
}
