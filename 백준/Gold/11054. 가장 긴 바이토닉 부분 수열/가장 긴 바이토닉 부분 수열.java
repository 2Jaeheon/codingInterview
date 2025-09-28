import java.util.*;

public class Main{

    static int N;
    static int[] arr;
    static Integer[] lisMemo; // LIS 결과를 저장할 메모이제이션 배열
    static Integer[] ldsMemo; // LDS 결과를 저장할 메모이제이션 배열
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        lisMemo = new Integer[N];
        ldsMemo = new Integer[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            int lisLength = findLIS(i);
            int ldsLength = findLDS(i);
            
            // LIS + LDS - 1 (봉우리 원소 중복 제거)
            maxLen = Math.max(maxLen, lisLength + ldsLength - 1);
        }

        System.out.println(maxLen);
    }

    public static int findLIS(int index) {
        // 이미 계산된 값이 있다면, 재계산 없이 즉시 반환
        if (lisMemo[index] == null) {
            lisMemo[index] = 1; // 기본값: 자기 자신만 포함하는 길이 1

            // index 이전의 원소들을 탐색
            for (int j = 0; j < index; j++) {
                if (arr[j] < arr[index]) {
                    // findLIS(j)를 재귀 호출하여 j까지의 LIS 길이를 얻어옴
                    lisMemo[index] = Math.max(lisMemo[index], findLIS(j) + 1);
                }
            }
        }
        
        return lisMemo[index];
    }

    // index를 시작 원소로 가지는 가장 긴 감소 부분 수열의 길이를 찾는 재귀 함수
    public static int findLDS(int index) {
        // 이미 계산된 값이 있다면, 재계산 없이 즉시 반환
        if (ldsMemo[index] == null) {
            ldsMemo[index] = 1; // 기본값: 자기 자신만 포함하는 길이 1

            // index 이후의 원소들을 탐색
            for (int j = index + 1; j < N; j++) {
                if (arr[j] < arr[index]) {
                    // findLDS(j)를 재귀 호출하여 j부터의 LDS 길이를 얻어옴
                    ldsMemo[index] = Math.max(ldsMemo[index], findLDS(j) + 1);
                }
            }
        }
        return ldsMemo[index];
    }
}
