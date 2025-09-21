import java.util.*;

public class Main{

    static int[][] board;
    static int n;
    static long count = 0;
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        // 퀸이 서로 공격하면 안 됨
        // 깊이가 8인 재귀 문제?
        // 퀸을 8개를 놔야하니까 깊이가 8이거나 더이상 놀 수 없을 때 백트레킹

        board = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(board[i], 0);
        }
    
        queen(0);
        System.out.println(count);
    }
    
    // row번째 행에 퀸 하나 배치
    public static void queen(int row) {
        if (row == n) { // 퀸 n개 성공
            count++;
            return;
        }
    
        for (int c = 0; c < n; c++) {
            if (board[row][c] != 0) 
                continue; // 이미 1(칠해짐) or 2(퀸)인 칸은 스킵
    
            // 현재 (row, c)에 퀸(2)을 놓아도 되는지 검사
            boolean ok = true;
    
            // 1) 같은 열 위쪽(이미 배치된 행들)에 퀸(2) 있는지
            for (int r = row - 1; r >= 0; r--) {
                if (board[r][c] == 2) { 
                    ok = false; break; 
                }
            }
    
            // 2) 좌상향 대각선
            for (int r = row - 1, cc = c - 1; ok && r >= 0 && cc >= 0; r--, cc--) {
                if (board[r][cc] == 2) { 
                    ok = false; 
                    break; 
                }
            }
    
            // 3) 우상향 대각선
            for (int r = row - 1, cc = c + 1; ok && r >= 0 && cc < n; r--, cc++) {
                if (board[r][cc] == 2) { 
                    ok = false; 
                    break; 
                }
            }
    
            if (!ok)
                continue;
    
            // 배치
            board[row][c] = 2;
            queen(row + 1);
            // 되돌리기
            board[row][c] = 0;
        }
    }

    public static void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
