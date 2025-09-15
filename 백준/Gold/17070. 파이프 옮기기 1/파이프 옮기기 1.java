import java.util.*;

public class Main{

    static int N;
    static int answer = 0;
    static int[][] arr;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 파이플를 밀 수 있는 방향은 세가지.
        // 파이프의 한쪽 끝을 N, N으로 이동하는 방법의 개수 
        N = sc.nextInt();
        // 가로일때랑 세로일때랑 대각선일때를 체크한 뒤 해야하는 것으로 이동을 시킴
        // 그러면 또 이동했을때의 상태를 체크한 뒤 벽을 체크해서 다음으로 이동시킴

        arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        dfs(new Pipe(0, 1, 0));
        
        System.out.println(answer);
    }

    public static void dfs(Pipe pipe) {
        // 종료조건
        if(pipe.r == N - 1 && pipe.c == N - 1) {
            answer++;
            return ;
        }

        // 다음 칸이 벽이라면 다음걸 진행해야함
        if(pipe.direction == 0) { // 오른쪽
            // 오른쪽으로 이동
            if (pipe.c + 1 < N && arr[pipe.r][pipe.c + 1] == 0) {
                dfs(new Pipe(pipe.r, pipe.c + 1, 0));
            }
            // 대각선으로 이동
            if (pipe.r + 1 < N && pipe.c + 1 < N && arr[pipe.r][pipe.c + 1] == 0 && arr[pipe.r + 1][pipe.c] == 0 && arr[pipe.r + 1][pipe.c + 1] == 0) {
                dfs(new Pipe(pipe.r + 1, pipe.c + 1, 2));
            }
        }

        if(pipe.direction == 1) { // 아래
            // 아래로 이동
            if (pipe.r + 1 < N && arr[pipe.r + 1][pipe.c] == 0) {
                dfs(new Pipe(pipe.r + 1, pipe.c, 1));
            }
            // 대각선으로 이동
            if (pipe.r + 1 < N && pipe.c + 1 < N && arr[pipe.r][pipe.c + 1] == 0 && arr[pipe.r + 1][pipe.c] == 0 && arr[pipe.r + 1][pipe.c + 1] == 0) {
                dfs(new Pipe(pipe.r + 1, pipe.c + 1, 2));
            }
        }

        if(pipe.direction == 2) { // 대각선 아래
            // 오른쪽으로 이동
            if (pipe.c + 1 < N && arr[pipe.r][pipe.c + 1] == 0) {
                dfs(new Pipe(pipe.r, pipe.c + 1, 0));
            }
            // 아래로 이동
            if (pipe.r + 1 < N && arr[pipe.r + 1][pipe.c] == 0) {
                dfs(new Pipe(pipe.r + 1, pipe.c, 1));
            }
            // 대각선으로 이동
            if (pipe.r + 1 < N && pipe.c + 1 < N && arr[pipe.r][pipe.c + 1] == 0 && arr[pipe.r + 1][pipe.c] == 0 && arr[pipe.r + 1][pipe.c + 1] == 0) {
                dfs(new Pipe(pipe.r + 1, pipe.c + 1, 2));
            }
        }
        
    }

    static class Pipe {
        int r;
        int c;
        int direction; // 0: 가로, 1: 세로, 2: 대각선

        public Pipe(int r, int c, int direction) {
            this.r = r;
            this.c = c;
            this.direction = direction;
        }
    }
}
