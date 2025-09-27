import java.io.*;
import java.util.*;

public class Main {

    static int N;
    final static int MOD = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int[][] arr = new int[N][N];
        int[][] result = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }

        // 단위 행렬로 초기화함.
        for(int i = 0; i < N; i++) {
            result[i][i] = 1;
        }

        while(B >= 1) {
            // 맨 오른쪽 비트가 1인지를 확인함
            // 만약 오른쪽 비트가 1이라면 A^B를 하는데 현재의 A의 거듭제곱이 필요함.
            if(B % 2 == 1) {
                result = multiply(result, arr);
            }

            arr = multiply(arr, arr);

            B /= 2;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static int[][] multiply(int[][] o1, int[][] o2) {
        int[][] ret = new int[N][N];

        // i: 행
        for (int i = 0; i < N; i++) {
            // j: 열
            for (int j = 0; j < N; j++) {
                // k: 실제 곱셈과 덧셈
                for (int k = 0; k < N; k++) {
                    ret[i][j] += o1[i][k] * o2[k][j];
                    ret[i][j] %= MOD;
                }
            }
        }
        
        return ret;
    }
}