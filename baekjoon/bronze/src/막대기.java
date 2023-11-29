import java.util.*;

public class 막대기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        int[] arr = new int[x];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        int stickCount = 1;
        int lastNum = arr[arr.length - 1];

        //for(int i = 0; i < arr.length; i++) 로 해서 에러발생
        //lastNUm = arr[i]를 쓰지 않아서 에러발생
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > lastNum) {
                stickCount++;
                lastNum = arr[i];
            }
        }
        System.out.println(stickCount);
    }
}
