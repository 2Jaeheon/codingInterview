import java.util.*;

public class Main{
    static int n, m;
    static boolean[] used;
    static int[] arr, picked;

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // N개의 자연수 중 M개를 고른 수열
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n];
        picked = new int[m];
        used = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        dfs(0);

    }

    public static void dfs(int depth) {
        if(depth == m) {
            for(int i = 0; i < m; i++) {
                System.out.print(picked[i] + " ");
            }
            System.out.println();
            return ;
        }

        // last는 같은 숫자로 시작하는 여러 분기를 막아줌
        int last = -1;

        for(int i = 0; i < n; i++) {
            if(used[i]) {
                continue;
            }

            if(arr[i] == last) {
                continue;
            }

            // 문제가 없는 경우
            used[i] = true;
            picked[depth] = arr[i];
            last = arr[i];
            dfs(depth + 1);
            used[i] = false;
        }
    }
}
