import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        long[] arr = new long[n];
        
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();           
        }

        long money = k;
        int sum = 0;
        for(int i = arr.length - 1; i >= 0; i--) {
            if(arr[i] > k) {
                continue;
            }
            // 4790
            // 4790 - 4 * 1000 = 790
            // 790 - 1 * 500 = 290
            // 290 - 2 * 100 = 90
            // 90 - 1 * 50 = 40
            // 40 - 4 * 10 = 0

            
            long temp = money / arr[i];
            money -= temp * arr[i];
            sum += temp;
        }
        System.out.println(sum);
    }
}