import java.util.*;

public class Main{

    static int n, m;
    static int[] arr, picked;
    static boolean[] visited;
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        picked = new int[m];
        arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        dfs(0, 0);
    }

    static void dfs(int depth, int start) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(picked[i] + " ");
            }
            System.out.println();
            return;
        }

        int last = -1;
        
        for (int i = start; i < n; i++) {
            if (arr[i] == last) { // 같은 깊이에서 직전에 쓴 값과 같으면 건너뛰기
                continue;
            }
            
            picked[depth] = arr[i];
            last = arr[i];
            dfs(depth + 1, i);
        }
    }
}
