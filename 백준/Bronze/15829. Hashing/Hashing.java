import java.util.*;

public class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int L = 31;
    long m = 1234567891;
    
    // 알파벳에서 96을 빼면 됨
    String str = sc.next();
    
    int[] arr = new int[str.length()];

    for(int i = 0; i < str.length(); i++){
      arr[i] = str.charAt(i) - 96;
    }

    int answer = 0;
    long r = 1;

    for (int i = 0; i < arr.length; i++) {
      answer = (int)((answer + arr[i] * r) % m);
      r = (r * L) % m;
    }
    
    System.out.println(answer);
  }
}
