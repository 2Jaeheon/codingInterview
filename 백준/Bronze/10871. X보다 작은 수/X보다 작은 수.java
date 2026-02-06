import java.util.Scanner;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int X = sc.nextInt();
    int[] A = new int[N];
    List<Integer> answer = new ArrayList<>();
    
    for (int i = 0; i < N; i++) {
      A[i] = sc.nextInt();

      if(A[i] < X) {
        answer.add(A[i]);
      }
    }

    for(int i = 0; i < answer.size(); i++) {
      System.out.print(answer.get(i) + " ");
    }
  }
}