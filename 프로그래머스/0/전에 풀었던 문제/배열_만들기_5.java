import java.util.*;

public class 배열_만들기_5 {

    class Solution {

        public int[] solution(String[] intStrs, int k, int s, int l) {
            List<Integer> list = new ArrayList<>();

            for (String str : intStrs) {
                String subStr = str.substring(s, s + l);
                int num = Integer.parseInt(subStr);
                if (num > k) {
                    list.add(num);
                }
            }

            return list.stream()
                .mapToInt(i -> i)
                .toArray();
        }
    }

}
