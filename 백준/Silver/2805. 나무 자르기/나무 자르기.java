import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 상근이는 나무 M미터가 필요함
        // 높이 H를 지정하면 톱날이 H미터 위로 올라감
        // 그리고 설정된 높이 아래의 한 줄의 나무를 모두 자름
        // 그리고 잘린 목재를 가지고 간다.
        // 나무를 필요한 만큼만 가져가는 것이 목표
        // 이 때 적어도 M미터의 나무를 가져가려면 설정가능한 높이의 최댓값


        // 나무의 수 N
        int n = sc.nextInt();
        // 집에 가져가려고 하는 나무의 길이 M
        long m = sc.nextLong();
        //나무의 개수
        long[] trees = new long[n];
        long max = -1;
        for(int i = 0; i < n; i++) {
            trees[i] = sc.nextLong();
            max = Math.max(max, trees[i]);
        }

        // 5 20
        // 4 26 40 42 46
        // 1 -> 1,000,000,000 -> 절반 -> 또 절반 이진 탐색

        long start = 1;
        long end = max;
        long cur = 0;
        long result = 0;

        while(start <= end) {
            long sum = 0;
            cur = (start + end) / 2;

            for (int i = 0; i < n; i++) {
                if (trees[i] > cur) {
                    sum += trees[i] - cur;
                }
            }

            // 너무 조금잘림. 더 높게 잘라도 됨.
            // 따라서 start = cur + 1;
            if (sum >= m) {
                result = cur;
                start = cur + 1;
            } else {
                // 덜 잘랐다면, 더 낮게 잘라야 함
                end = cur - 1;
            }
        }

        System.out.println(result);
    }
}