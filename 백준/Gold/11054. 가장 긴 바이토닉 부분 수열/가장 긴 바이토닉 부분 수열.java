import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 바이토닉 부분 수열
        // 1, 2, 3, 4, 3, 2, 1 이런식의 수열 
        // 주어진 수열 중 가장 긴 바이토닉 수열의 길이

        // 가장 긴 바이토닉 수열의 길이이기 때문에 DP로 접근해야함은 인지함.
        // 어쩌면 DFS로도 풀 수 있지 않을까?
        // 0번째에서 시작해서 재귀를 통해 파고파고 들어가보고 가장 긴 길이를 반환해주면 되지 않을까

        // DFS로 풀 수 있을까? 
        // N과 A는 각 1000개 -> 메모이제이션 + DFS로 풀어보자
        int n = sc.nextInt();
        int[] arr = new int[n];

        // DP 배열: LIS와 LDS결과를 저장
        int[] lisDp = new int[n]; // Longest Increasing Subsequence
        int[] ldsDp = new int[n]; // Longest Decreasing Subsequence

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // LIS 계산
        for (int i = 0; i < n; i++) {
            lisDp[i] = 1; // 모든 원소는 그 자체로 길이 1의 수열
            
            for (int j = 0; j < i; j++) {
                // 현재 원소(arr[i])가 이전 원소(arr[j])보다 크면
                // 증가 수열을 이어갈 수 있음

                // arr[i] 가 현재 선택한 원소
                // arr[j] 는 처음부터 현재 선택한 원소까지 중 j번째 원소
                // lisDp[i]: arr[i]라는 원소를 반드시 포함하면서, 그 원소로 끝나는 가장 긴 증가 부분 수열(LIS)의 길이
                if (arr[j] < arr[i]) {
                    lisDp[i] = Math.max(lisDp[i], lisDp[j] + 1);
                }
            }
        }

        

        // LDS 계산
        for (int i = n - 1; i >= 0; i--) {
            ldsDp[i] = 1; // 모든 원소는 그 자체로 길이 1의 수열
            for (int j = n - 1; j > i; j--) {
                // 현재 원소(arr[i])가 이후 원소(arr[j])보다 크면
                // 감소 수열을 이어갈 수 있음
                if (arr[j] < arr[i]) {
                    ldsDp[i] = Math.max(ldsDp[i], ldsDp[j] + 1);
                }
            }
        }

        // find the maximum
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            // 각 i를 봉우리로 했을 때의 바이토닉 수열 길이 계산
            // LIS 길이 + LDS 길이 - 1 (봉우리 원소가 중복되므로)
            int currentLen = lisDp[i] + ldsDp[i] - 1;
            if (maxLen < currentLen) {
                maxLen = currentLen;
            }
        }

        System.out.println(maxLen);
    }
}
