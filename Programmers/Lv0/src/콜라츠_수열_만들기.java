import java.util.*;

public class 콜라츠_수열_만들기 {

    public int[] solution(int n) {
        int[] answer = new int[countArr(n) + 1];
        int i = 1;

        answer[0] = n;
        while (true) {
            if (n == 1) {
                return answer;
            }
            if (n % 2 == 0) {
                n /= 2;
                answer[i++] = n;
            } else {
                n = n * 3 + 1;
                answer[i++] = n;
            }
        }
    }

    public static int countArr(int n) {
        int count = 0;
        while (true) {
            if (n == 1) {
                return count;
            }
            if (n % 2 == 0) {
                n /= 2;
                count++;
            } else {
                n = n * 3 + 1;
                count++;
            }
        }
    }

    //2번 풀이

    public int[] solution2(int n) {
        Queue<Integer> answer = new LinkedList<>();
        while (n > 1) {
            answer.add(n);
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = n * 3 + 1;
            }
        }
        answer.add(1);
        return answer.stream()
            .mapToInt(i -> i)
            .toArray();

        //스트림을 만든 후 int형으로 매핑하고 어레이로 변환해서 반환
    }
}
