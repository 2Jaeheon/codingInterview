import java.util.*;

public class 빈_배열에_추가_삭제하기 {

    class Solution {

        public int[] solution(int[] arr, boolean[] flag) {
            List<Integer> list = new LinkedList<>();

            for (int i = 0; i < arr.length; i++) {
                if (flag[i] == true) {
                    for (int j = 0; j < arr[i] * 2; j++) {
                        list.add(arr[i]);
                    }
                } else if (flag[i] == false) {
                    for (int k = 0; k < arr[i]; k++) {
                        list.remove(list.size() - 1);
                    }
                }
            }
            int[] answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }

            return answer;
        }
    }
}
