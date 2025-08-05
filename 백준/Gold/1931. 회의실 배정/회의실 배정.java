import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1개의 회의실
        // N개의 회의에 대해서 사용표를 만들 것
        // 각 회의 I에 대해 시작시간과 끝나는 시간이 주어짐
        // 각 회의가 겹치지 않으며 사용할 수 있는 회의의 최대 개수

        int n = sc.nextInt(); // 회의시간

        // thinking
        /*
        우선 크기를 통해서 생각해보자. N의 크기는 100,000 -> 브루트포스로 하면 무조건 시간초과
        브루트포스가 아닌 다른 방법을 택해야함.

        시작시간, 종료시간, 차이를 하나의 구조에 저장하고
        크기순으로 정렬해서
        0 - 6 / 1 - 4 후자가 시작은 더 큰데 종료시간이 더 작음
        이러한 것들을 찾아서 하면 되지 않을까?
        0 - 6 / 1 - 4 후자
        1 - 4 / 3 - 5 종료시간 매칭 안 됨 -> 반려
        1 - 4 / 3 - 8 종료시간 매치 안 됨 -> 반려
        1 - 4 / 5 - 7 가능 -> 한 번 더 비교 (5 - 7 / 5 - 9) 중 차이가 더 적은 거 선택
        */

        int[][] arr = new int[n][2];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 2; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        // System.out.println(Arrays.deepToString(arr));
        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) 
                return a[0] - b[0];
            
            return a[1] - b[1];
        });
        // System.out.println("\n\n" + Arrays.deepToString(arr));

        int count = 0;
        int lastEnd = 0;
        
        for (int i = 0; i < n; i++) {
            if (arr[i][0] >= lastEnd) { // 겹치지 않으면
                count++;
                lastEnd = arr[i][1];
            }
        }
        
        System.out.println(count);
    }
}