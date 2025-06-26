import java.util.*;

public class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);

    int a = sc.nextInt();
    int b = sc.nextInt();
    int v = sc.nextInt();

    // 1 ~ (v - 1) 까지는 a-b만큼 상승
    // v날에는 a만큼 상승
    // 즉, v-a까지는 계속 올라가기만 하면 되고, 마지막 날에는 a만큼만 올라가면 끝
    int day = (int)Math.ceil((double)(v - a) / (a - b)) + 1;
    // 전체 날이 v-a이고, 이거를 a-b로 얼마만큼 갔는지를 알고싶은 것
    System.out.println(day);
  }
}
