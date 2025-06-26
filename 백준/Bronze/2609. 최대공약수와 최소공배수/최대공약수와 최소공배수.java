import java.util.*;

public class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);

    int a, b;

    a = sc.nextInt();
    b = sc.nextInt();

    int gcd = 1;
    int lcm = 1;
    for(int i = 2; i < 100000; i++){
      if(a % i == 0 && b % i == 0){
        a /= i;
        b /= i;
        gcd *= i;
        i = 1;
      }
    }
    
    lcm = gcd * a * b;
    System.out.println(gcd);
    System.out.println(lcm);

    // 24 18
    // /2 => 12 9
    // /3 => 4 3
  }
}
