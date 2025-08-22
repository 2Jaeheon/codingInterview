import java.util.*;

public class Main{
    static int N, M;

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 자연수 N과 M이 주어짐.
        N = sc.nextInt();
        M = sc.nextInt();

        int[] sequence = new int[M];
        dfs(0, 1, sequence);
    }

    public static void dfs(int depth, int start, int[] sequence) {
        if (depth == M) {
            for (int num : sequence) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }
    
        for (int i = start; i <= N; i++) {
            sequence[depth] = i;
            dfs(depth + 1, i, sequence);
        }
    }
}
