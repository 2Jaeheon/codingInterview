import java.util.*;

public class Main{
    static int[][] matrix;
    static int[][] sum;
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        matrix = new int[n + 1][n + 1];
        sum = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                matrix[i][j] = sc.nextInt();
                // sum[i][j] = 현재값 + 위쪽까지의총합 + 왼쪽까지의총합 − 두번 더해진 대각선위총합
                sum[i][j] = matrix[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }
        
        for(int i = 0; i < m; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            int answer = find(x1, y1, x2, y2);
            System.out.println(answer);
        }
    }

    public static int find(int x1, int y1, int x2, int y2) {
        // 결과는 끝값에서 구하는 범위를 다 빼버림. 근데 좌측 상단 모서리가 두번 빠져서 한 번 더해줌
        int result = sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
        return result;
    }
}
