import java.util.*;

public class Main{

    static char[][] board;
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // n은 3 * 2^k

        int H = N;
        int W = 2 * N - 1;
        board = new char[H][W];

        for(int i = 0; i < H; i++) {
            Arrays.fill(board[i], ' ');
        }

        triangle(0, N - 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < H; i++) {
            sb.append(board[i]).append('\n');
        }
        System.out.print(sb);
    }

    // 꼭짓점 (r,c), 높이 n인 정삼각형을 찍는다
    // 0, 23, 24
    static void triangle(int r, int c, int n) {
        if (n == 3) {
            board[r][c] = '*';
            board[r + 1][c - 1] = '*';
            board[r + 1][c + 1] = '*';
            for (int j = -2; j <= 2; j++) {
                board[r + 2][c + j] = '*';
            }
            return;
        }
        
        int half = n / 2;
        triangle(r, c, half);                 // 위 삼각형
        triangle(r + half, c - half, half);   // 왼쪽 아래 삼각형
        triangle(r + half, c + half, half);   // 오른쪽 아래 삼각형
    }
}
