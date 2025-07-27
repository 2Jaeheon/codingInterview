import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        // 두가지 종료의 작업이 가능
        // 1. 블럭 캐기 = 2s
        // 2. 블럭 놓기 = 1s

        // 땅을 고르는데 걸리는 시간과 땅의 높이를 출력
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int b = Integer.parseInt(input[2]);

        int[] heightCount = new int[257];
        int minH = 256;
        int maxH = 0;

        
        // 땅 높이 입력 받으며 heightCount에 누적
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int h = Integer.parseInt(line[j]);
                heightCount[h]++;
                minH = Math.min(minH, h);
                maxH = Math.max(maxH, h);
            }
        }
        
        int minTime = Integer.MAX_VALUE;
        int resultHeight = 0;

        // 기준 높이를 시도
        for (int target = minH; target <= maxH; target++) {
            int time = 0;
            int inventory = b;

            for (int h = 0; h <= 256; h++) {
                int count = heightCount[h];
                int diff = h - target;

                if (diff > 0) { // 블럭 제거
                    time += diff * 2 * count;
                    inventory += diff * count;
                } else if (diff < 0) { // 블럭 추가
                    time += -diff * count;
                    inventory -= -diff * count;
                }
            }

            if (inventory < 0) continue;

            if (time < minTime || (time == minTime && target > resultHeight)) {
                minTime = time;
                resultHeight = target;
            }
        }

        System.out.println(minTime + " " + resultHeight);
    }
}