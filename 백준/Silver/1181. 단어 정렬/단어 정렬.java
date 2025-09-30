import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();
        Set<String> set = new HashSet<>();
        
        for(int i = 0; i < n; i++) {
            set.add(sc.nextLine());
        }
        
        String[] strArr = set.toArray(new String[0]);
        Arrays.sort(strArr);
        Arrays.sort(strArr, Comparator.comparingInt(a -> a.length()));

        for(String str : strArr) {
            System.out.println(str);
        }
    }
}
