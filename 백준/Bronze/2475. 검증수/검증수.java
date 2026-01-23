import java.util.*;
import java.math.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        
        for(int i = 0; i < 5; i++) {
            int x = sc.nextInt();
            int s = (int)Math.pow(x, 2);
            sum += s;
        }

        System.out.println(sum % 10);
    }
}
