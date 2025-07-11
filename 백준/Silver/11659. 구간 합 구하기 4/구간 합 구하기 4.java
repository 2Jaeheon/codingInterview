import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n + 1];
        // 누적합을 저장할 배열
        // prefixSum[1] = 1번째 원소값
        // prefixSum[2] = 1, 2번째 원소값의 합
        // prefixSum[3] = 1, 2, 2번째 원소값의 합
        int[] prefixSum = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        for(int i = 0; i < m; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            
            int sum = prefixSum[second] - prefixSum[first - 1];
            System.out.println(sum);
        }
    }
}