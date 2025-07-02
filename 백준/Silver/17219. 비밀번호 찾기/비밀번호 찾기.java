import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Map<String, String> passwordList = new HashMap<>();

        for(int i = 0; i < n; i++) {
            String address = sc.next();
            String password = sc.next();

            passwordList.put(address, password);
        }

        for(int i = 0; i < m; i++) {
            String query = sc.next();

            System.out.println(passwordList.get(query));
        }
    }
}