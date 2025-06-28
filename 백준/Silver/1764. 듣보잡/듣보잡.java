import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 듣도 못한 사람 명단
        // 보도 못한 사람 명단
        // 듣도 보도 못한 사람 명단을 출력
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            map.put(sc.next(), 1);
        }

        for(int j = 0; j < m; j++) {
            String str = sc.next();

            if(!map.containsKey(str)) { // 만약 들어있지 않다면
                
            } else { // 들어있음.
                list.add(str);
            }
        }

        System.out.println(list.size());
        list.stream().sorted().forEach(System.out::println);
    }
}