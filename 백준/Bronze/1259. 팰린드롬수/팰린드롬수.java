import java.util.*;

public class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    
    while(true) {
      int x = sc.nextInt();

      if(x == 0){
        break;
      }

      if(isPalindrome(x)){
        System.out.println("yes");
      } else {
        System.out.println("no");
      }
    }
  }

  public static boolean isPalindrome(int x){
    int i = 0;
    int j = String.valueOf(x).length() - 1;

    int[] arr = new int[j + 1];

    for(int k = 0; k < j + 1; k++){
      arr[k] = String.valueOf(x).charAt(k) - '0';
    }

    while(i < j){
      // 맨 앞과 맨 뒤
      if(arr[i] == arr[j]){
        i++;
        j--;
        continue;
      } else {
        return false;
      }
    }

    return true;
  }
}
