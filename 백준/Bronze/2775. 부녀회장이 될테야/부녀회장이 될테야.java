import java.util.*;

public class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);

    // 처음에 테스트 케이스 수 주어짐
    // 각 케이스마다 k n이 첫 번째줄 두 번째 줄로 주어짐

    // k층의 n호에는 몇 명이 사는가
    // (k-1)층의 1호 ~ b호까지 사람들의 수의 합
    // 아파트는 0층부터, 각 층은 1호부터
    // 0층의 i호에는 i명이 삼

    int t = sc.nextInt();
    int k, n = 0;
    
    // 1층의 3호 => 0층의 1, 2, 3호의 합 = 6
    // 2층의 3호 => 1층의 1, 2, 3호의 합 = 1 + 3 + 6 = 10
    int[][] arr = new int[15][15];
    
    for(int i = 1; i <= 14; i++) {
      arr[0][i] = i;
    }

    for(int i = 1; i <= 14; i++){
      for(int j = 1; j <= 14; j++){
        // 전 층의 현재 호수의 사람 수 + 현재 층의 전 호수의 사람 수
        arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
      }
    }

    for(int i = 0; i < t; i++){
      k = sc.nextInt();
      n = sc.nextInt();  
      System.out.println(arr[k][n]);
    }
  }
}
