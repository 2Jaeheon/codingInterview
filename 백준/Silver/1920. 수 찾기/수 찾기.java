import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // N개의 정수가 주어질 때 X가 존재하는지를 알아내는 프로그램
        // N의 범위가 100_000 -> 배열로 하면 너무 오래걸릴 거 같음 O(NM) => O(100_000 * 100_000)이므로 분명 초과할것
        // 메모리 제한은 128MB, N 범위가 100_000 => Hash 테이블을 사용하면 됨
        // Map으로 구현하면 되지 않을까? 있는 것만 따로 다 넣고, contains() 메서드로 존재하는 것만 탐색
        // 존재하면 1 존재하지 않으면 0
        // 근데 Map보다 Set이 더 효율적일 것 같음 => 값 유무만 판별하면 되니까
        // 시간복잡도는 O(N + M) => 선형적이고 충분히 빠름
        
        int n = sc.nextInt();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            int input = sc.nextInt();
            set.add(input);
        }

        int m = sc.nextInt(); // M
        for(int i = 0; i < m; i++) {
            int query = sc.nextInt();

            if(set.contains(query)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
