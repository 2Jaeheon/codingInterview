package tree.num26;

public class 예상대진표 {

    public static void main(String[] args) {
        System.out.println(solution(8, 4, 7));
        System.out.println(solution2(8, 4, 7));
    }

    public static int solution(int n, int a, int b) {

        int count = 1;
        int x = 0, y = 0;

        for (int i = 0; i < 20; i++) {
            //check even or odd
            if (a % 2 != 0) {
                x = ++a;
            } else {
                x = a;
            }
            if (b % 2 != 0) {
                y = ++b;
            } else {
                y = b;
            }
            if (x == y) {
                return count;
            } else {
                count++;
                a = x / 2;
                b = y / 2;
            }
        }
        return count;
    }

    // new Solution
    public static int solution2(int n, int a, int b) {
        int answer = 0;

        for (answer = 0; a != b; answer++) {
            a = (a + 1) / 2;
            b = (b + 1) / 2;
        }

        return answer;
    }
}
