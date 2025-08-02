import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N + 1개의 I와 N개의 O -> I와 O가 교대로 나옴
        int n = sc.nextInt();
        int m = sc.nextInt();

        int count = 0;
        int result = 0;
        String str = sc.next();
        int i = 0;

        while(i <= m - 3) { // 최소 3글자가 넘어야 함.
            char x = str.charAt(i);
            char y = str.charAt(i + 1);
            char z = str.charAt(i + 2);

            if(x == 'I' && y == 'O' && z == 'I') {
                count++;

                if(count >= n) { // count가 n 이상이면 result를 하나 추가
                    // 즉, IOI 패턴이면서 현재 누적된 IOI의 개수를 구해서 N보다 클 때 result에 추가해줌
                    // count로 중복 패턴을 조사
                    result++;
                }

                // 다음 IOI는 현재 IOI의 마지막 'I'에서 시작 가능
                i += 2;
            } else {
                // 연속이 끊기면 다시 시작
                count = 0;
                i++;
            }
        }

        System.out.println(result);
    }
}