import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] maxDP = new int[3];
        int[] minDP = new int[3];

        for (int i = 0; i < n; i++) {
            // 1. 입력을 받고
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 2. 갱신에 사용할 '이전 DP 값'들을 미리 저장
            int prevMax0 = maxDP[0], prevMax1 = maxDP[1], prevMax2 = maxDP[2];
            int prevMin0 = minDP[0], prevMin1 = minDP[1], prevMin2 = minDP[2];

            // 3. DP 배열을 '즉시' 갱신 (임시 변수 tempA, B, C가 필요 없어짐)
            maxDP[0] = a + Math.max(prevMax0, prevMax1);
            maxDP[1] = b + Math.max(prevMax0, Math.max(prevMax1, prevMax2));
            maxDP[2] = c + Math.max(prevMax1, prevMax2);

            minDP[0] = a + Math.min(prevMin0, prevMin1);
            minDP[1] = b + Math.min(prevMin0, Math.min(prevMin1, prevMin2));
            minDP[2] = c + Math.min(prevMin1, prevMin2);
        }

        int finalMax = Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2]));
        int finalMin = Math.min(minDP[0], Math.min(minDP[1], minDP[2]));

        System.out.println(finalMax + " " + finalMin);
    }
}