import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();

        // N - 1의 사이즈로 4등분해서 재귀적으로 방문하는 것.
        int startPoint = 0;
        int size = n * n;

        while (n > 0) {
            int half = (int) Math.pow(2, n - 1);  // 현재 블록의 절반 길이
            int quarterSize = half * half; // 한 사분면의 칸 수
        
            // r, c 위치를 기반으로 사분면 판별
            if (r < half && c < half) {          // 1사분면 (왼쪽 위)
                // startPoint += 0 * quarterSize;
            } else if (r < half && c >= half) {  // 2사분면 (오른쪽 위)
                startPoint += 1 * quarterSize;
                c -= half; // 내부 좌표로 이동
            } else if (r >= half && c < half) {  // 3사분면 (왼쪽 아래)
                startPoint += 2 * quarterSize;
                r -= half;
            } else {                             // 4사분면 (오른쪽 아래)
                startPoint += 3 * quarterSize;
                r -= half;
                c -= half;
            }
        
            n--; // 더 작은 블록으로 들어감
        }

        System.out.println(startPoint);
    }
}