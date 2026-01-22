import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        long result = 1;
        int x = sc.nextInt();

        for(int i = 1; i <= x; i++) {
            result *= i;
        }

        System.out.println(result);
    }
}
