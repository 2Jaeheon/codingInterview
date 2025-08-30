import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int x = -1;
        int count = 0;
        
        // 164 -> 82 -> 41 -> 4
        // 162 -> 81 -> 8 -> 4 -> 2
        // 1671 -> 167 

        while(a < b) {
            if(b % 10 == 1){
                b /= 10;
                count++;
            } else if(b % 2 == 0) {
                b /= 2;
                count++;
            } else {
                break;
            }
        }

        if(a != b) {
            System.out.println(-1);
        } else {
            System.out.println(count + 1);
        }
    }
}
