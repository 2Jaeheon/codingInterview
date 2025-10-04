import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] b = new int[m];
        for(int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }

        List<Integer> result = new ArrayList<>();
        // 각 배열의 탐색 시작 위치를 가리키는 포인터
        int idxA = 0;
        int idxB = 0;

        while(idxA < n && idxB < m) {
            int bestVal = -1;  // 이번 단계에서 찾은 최적(가장 큰)의 공통 값
            int nextIdxA = -1; // bestVal이 위치한 A 배열의 인덱스
            int nextIdxB = -1; // bestVal이 위치한 B 배열의 인덱스

            for (int v = 100; v >= 1; v--) {
                int findA = -1;
                int findB = -1;

                // A에서 v가 존재하는지를 탐색
                for (int i = idxA; i < n; i++) {
                    if (a[i] == v) {
                        findA = i;
                        break;
                    }
                }

                // B에서 v가 존재하는지를 탐색
                if (findA != -1) {
                    for (int j = idxB; j < m; j++) {
                        if (b[j] == v) {
                            findB = j;
                            break;
                        }
                    }
                }
                
                // 찾지 못하면 패스
                if (findA != -1 && findB != -1) {
                    bestVal = v;
                    nextIdxA = findA;
                    nextIdxB = findB;
                    break; 
                }
            }

            // 공통 원소를 찾았을 때
            if (bestVal != -1) { 
                result.add(bestVal);
                idxA = nextIdxA + 1;
                idxB = nextIdxB + 1;
            } else {
                break;
            }
        }

        System.out.println(result.size());
        if (!result.isEmpty()) {

            for(int answer : result) {
                System.out.print(answer + " ");
            }
            
            System.out.println();
        }
    }
}
