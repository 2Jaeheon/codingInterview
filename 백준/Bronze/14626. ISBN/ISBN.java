import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        int[] arr = new int[str.length()];
        int starIndex = 0;
        int check = arr[arr.length - 1];
        
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '*'){
                starIndex = i;
                arr[i] = -1;
                continue;
            }
            
            arr[i] = str.charAt(i) - '0';
        }

        for(int i = 0; i <= 9; i++) {
            arr[starIndex] = i;
            int sum = 0;
            
            for(int j = 0; j < arr.length; j++) {
                if(j % 2 == 0){
                    sum += 1 * arr[j];
                } else {
                    sum += 3 * arr[j];
                }
            }

            int m = (10 - (sum % 10)) % 10;
            if(m == check){
                System.out.println(i);
                break;
            }
        }
    }
}