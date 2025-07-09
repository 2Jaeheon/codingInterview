import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();

        // 네 번째 앞의 원소와 더하면 다음 숫자가 나온다.
        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();
            long[] arr = new long[x];
            if(x == 1) {
                System.out.println(1);
                continue;
            } else if(x == 2) {
                System.out.println(1);
                continue;
            } else if(x == 3) {
                System.out.println(1);
                continue;
            } else if(x == 4) {
                System.out.println(2);
                continue;
            } else if(x == 5) {
                System.out.println(2);
                continue;
            }

            // 네 번째 것까지 모두 초기화 
            for(int j = 0; j < 3; j++) {
                arr[j] = 1;
            }

            arr[3] = 2;
            arr[4] = 2;

            // 입력받은 x까지 반복해서 처리
            for(int j = 5; j < x; j++) {
                arr[j] = arr[j-1] + arr[j-5]; 
            }

            System.out.println(arr[x - 1]);
        }
    }
}