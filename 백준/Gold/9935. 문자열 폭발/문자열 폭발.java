import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 폭발 문자열이 없을 때까지 계속해서 없애야함.
        // 문자열을 하나씩 확인해보면서 스택에다가 넣는 식으로 하면 되지 않을까
        String str = sc.next();
        String target = sc.next();
        int m = target.length();
        StringBuilder sb = new StringBuilder();
        
        int idx;
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));

            // 끝 m글자가 target과 같은지 확인
            if (sb.length() >= m) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (sb.charAt(sb.length() - m + j) != target.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                // 같으면 터뜨림(삭제)
                if (match) sb.setLength(sb.length() - m);
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}
