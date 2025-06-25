import java.util.*;

public class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    double[] scores = new double[n];
    for(int i = 0; i < n; i++){
      scores[i] = sc.nextInt();
    }

    Arrays.sort(scores);
    double max = scores[n - 1];
    
    for(int i = 0; i < n; i++){
      scores[i] = (scores[i] / max) * 100;
    }

    double sum = Arrays.stream(scores).sum();
    System.out.println(sum / n);
  }
}
