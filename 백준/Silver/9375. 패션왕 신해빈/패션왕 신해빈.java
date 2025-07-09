    import java.util.*;
    
    public class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            
            
            for(int i = 0; i < t; i++) {
                int n = sc.nextInt();
                Map<String, Integer> map = new HashMap<>();
                
                for(int j = 0; j < n; j++) {
                    String product = sc.next();
                    String classification = sc.next();
                    
                    map.put(classification, map.getOrDefault(classification, 0) + 1);
                }
    
                int result = 1;
                for (int count : map.values()) {
                    result *= (count + 1);
                }
                System.out.println(result - 1);
            }
        }
    }