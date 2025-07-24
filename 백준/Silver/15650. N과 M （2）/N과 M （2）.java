import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 자연수 N, M이 주어질 때 다음을 만족하는 길이가 M인 모든 수열
        // 1부터 N까지 자연수 중에서 중복없이 M개를 고른 수열
        // 고른 수열은 오름차순

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        int depth = 0;
        int start = 1;

        recur(1, 0, list, n, m);
    }

    public static void recur(int start, int depth, List<Integer> list, int n, int m) {
        // 종료 조건 depth와 m이 같아졌을 때
        if(depth == m) {
            for(int num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        // m개가 아직 다 차지 않았다면 추가
        // start보다 하나 더 큰 값을 추가했기 때문에 오름차순으로 됨.
        for(int i = start; i <= n; i++) {
            // // 리스트를 만들고
            // List<Integer> next = new ArrayList<>(list);
            // // 리스트에 추가해서
            // next.add(i);
            // // start 값과 depth 값을 1씩 추가해서 재귀 
            // recur(i + 1, depth + 1, next, n, m);

            list.add(i); // push
            recur(i + 1, depth + 1, list, n, m);
            list.remove(list.size() - 1); // pop
        }
    }
}