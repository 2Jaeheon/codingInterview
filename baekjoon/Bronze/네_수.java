import java.util.*;

public class 네_수 {

    public static void main(String[] args) {
        int a, b, c, d;
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();

        String aStr = a + "";
        String bStr = b + "";
        String cStr = c + "";
        String dStr = d + "";

        String firstTemp = aStr + bStr;
        String secondTemp = cStr + dStr;

        int temp1 = Integer.parseInt(firstTemp);
        int temp2 = Integer.parseInt(secondTemp);

        System.out.println(temp1 + temp2);
    }
}
