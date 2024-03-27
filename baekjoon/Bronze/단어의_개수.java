import java.util.*;

public class 단어의_개수 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        str = str.trim();
        String[] tmp = str.split(" ");

        if (str.length() < 1) {
            System.out.println(0);
        } else {
            System.out.println(tmp.length);
        }
    }
}
