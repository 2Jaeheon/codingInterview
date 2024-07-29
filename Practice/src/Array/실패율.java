package Array;

import java.util.Arrays;
import java.util.List;
import java.util.*;

public class 실패율 {

    public static void main(String[] args) {
        int N = 5; //최대 스테이지의 수
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] stages2 = {2, 1, 2, 6, 2, 4, 3, 3, 5, 1};

        System.out.println(Arrays.toString(solution(N, stages)));
        //System.out.println(Arrays.toString(solution(N, stages2)));
    }

    public static int[] solution(int N, int[] stages) {
        int k = 0;
        int[] answer = new int[N];
        int len = stages.length;
        int count = 0;

        //index번호와 실패율을 각각 저장할 해쉬 맵 선언
        HashMap<Integer, Float> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            count = 0;
            int stageNum = i + 1;

            for (int j = 0; j < stages.length; j++) {
                if (stageNum == stages[j]) {
                    count++;
                }
            }

            //Divide by zero를 해결하기 위한 len이 0일때와 0이 아닐 때로 분류
            if (len == 0) {
                map.put(stageNum, 0f);
            } else {
                float failureRate = ((float) count / len);
                map.put(stageNum, failureRate);
                len -= count;
            }
        }

        //맵에 들어있는 key값과 value쌍을 바탕으로 entrySet을 얻은 후 value값을 통해서 내림차순 정렬 후 키를 배열로 반환
        answer = map.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // value 기준 내림차순 정렬
            .mapToInt(Map.Entry::getKey) // key 값을 int로 변환
            .toArray(); // 배열로 변환

        return answer;
    }
}
