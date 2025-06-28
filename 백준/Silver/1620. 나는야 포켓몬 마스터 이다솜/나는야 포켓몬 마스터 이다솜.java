import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        
        Map<String, Integer> nameMap = new HashMap<>();
        Map<Integer, String> numberMap = new HashMap<>();
        
        for(int i = 1; i <= n; i++){
            String str = sc.next();

            nameMap.put(str, i);
            numberMap.put(i, str);
        }

        for(int i = 0; i < m; i++){
            String input = sc.next();
            int x = 0;
            
            try {
                x = Integer.parseInt(input);
            } catch(Exception e){
                
            }

            
            if(x != 0) { // 숫자일 때
                System.out.println(numberMap.get(x));
            } else { // 문자열일 때
                System.out.println(nameMap.get(input));
            }
            
        }
    }
}