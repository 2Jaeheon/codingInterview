import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 소인수분해 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();

        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                list.add(i);
                n /= i;
            }
        }

        // 마지막 남은 소수도 추가
        if (n > 1) {
            list.add(n);
        }

        Collections.sort(list); // 이미 정렬된 상태지만 명확성을 위해 추가

        for (int num : list) {
            System.out.println(num);
        }

    }
}
