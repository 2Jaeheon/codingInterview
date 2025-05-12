package tree.num26;

import java.util.LinkedList;
import java.util.List;

public class review_예상대진표 {

    public static void main(String[] args) {
        System.out.println(solution(8, 4, 7));
        //System.out.println(solution2(8, 4, 7));
    }

    // 1 2 3 4 5 6 7 8 (1)
    // 4 to 2, 7 to 4
    // 1 2 3 4(2)
    // 2 to 1, 4 to 2
    // 1 2 (3)
    // 1
    public static int solution(int n, int a, int b) {
        int round = 0;
        for (int i = 1; i < n; i++) {
            if (a == b) {
                break;
            }
            a = (a + 1) / 2;
            b = (b + 1) / 2;

            round++;
        }

        return round;
    }
}
