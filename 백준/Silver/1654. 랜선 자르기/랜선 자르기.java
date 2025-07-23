import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        int n = sc.nextInt();
        long[] arr = new long[k];
        long start = 1;
        long end = arr[0];
        long result = 0;
        
        for(int i = 0; i < k; i++) {
            arr[i] = sc.nextInt();
            end = Math.max(end, arr[i]);
        }
        
        while(start <= end) {
            long mid = (start + end) / 2;
            
            if(isPossible(arr, mid, n)) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }

    public static boolean isPossible(long[] arr, long l, long n) {
        long sum = 0;
        
        for(int i = 0; i < arr.length; i++) {
            sum += arr[i] / l;
            if(n <= sum) {
                return true;
            }
        }

        return false;
    }
}