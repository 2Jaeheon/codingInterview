import java.util.*;

public class factorial2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long N = scanner.nextLong();

        long sum = factorial(N);
        System.out.println(sum);
    }

    public static long factorial(long n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
