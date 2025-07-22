import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        String[] minusParts = str.split("-");

        int result = 0;

        String[] first = minusParts[0].split("\\+");
        for(int i = 0; i < first.length; i++) {
            result += Integer.parseInt(first[i]);
        }

        for(int i = 1; i < minusParts.length; i++) {
            String[] plusGroup = minusParts[i].split("\\+");
            int sum = 0;
            for(int j = 0; j < plusGroup.length; j++) {
                sum += Integer.parseInt(plusGroup[j]);
            }
            result -= sum;
        }

        System.out.println(result);
    }
}