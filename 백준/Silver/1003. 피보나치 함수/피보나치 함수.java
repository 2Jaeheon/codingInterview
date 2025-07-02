import java.util.*;
public class Main {
    static int[][] count = new int[41][2];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        fibonacci();
        
        for(int i = 0; i < T; i++) {
            int x = sc.nextInt(); 
            System.out.println(count[x][0] + " " + count[x][1]);
        }
    }

    public static void fibonacci() {
        count[0][0] = 1;
        count[0][1] = 0;

        count[1][0] = 0;
        count[1][1] = 1;

        for(int i = 2; i <= 40; i++) {
            count[i][0] = count[i - 1][0] + count[i - 2][0];
            count[i][1] = count[i - 1][1] + count[i - 2][1];
        }
     }
}