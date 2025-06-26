import java.util.*;

public class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);

    int a = sc.nextInt();
    int b = sc.nextInt();
    int answer = factorial(a) / (factorial(a - b) * factorial(b));

    System.out.println(answer);
  }

  public static int factorial(int a){
    int sum = 1;
    for(int i = 1; i <= a; i++){
      sum *= i;
    }
    return sum;
  }
}
