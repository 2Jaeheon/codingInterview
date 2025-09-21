import java.util.*;

public class Main {
    static int n;
    static boolean[] col;      // 열 점유 여부
    static boolean[] diag1;    // (↘ 대각선)
    static boolean[] diag2;    // (↙ 대각선)
    static long count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        col   = new boolean[n];
        diag1 = new boolean[2*n - 1];
        diag2 = new boolean[2*n - 1];

        dfs(0);
        System.out.println(count);
    }

    static void dfs(int row) {
        if (row == n) { // 퀸 n개 성공 배치
            count++;
            return;
        }
        
        for (int c = 0; c < n; c++) {
            int d1 = row + c;
            int d2 = row - c + (n - 1);
            if (col[c] || diag1[d1] || diag2[d2]) 
                continue;

            col[c] = diag1[d1] = diag2[d2] = true;
            dfs(row + 1);
            col[c] = diag1[d1] = diag2[d2] = false;
        }
    }
}