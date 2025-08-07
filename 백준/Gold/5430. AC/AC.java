import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // AC라는 언어를 만들었음
        // 두 가지 동작 R(뒤집기) & D(버리기)

        int t = sc.nextInt(); // 테스트 개수

        for(int turn = 0; turn < t; turn++) {
            String p = sc.next(); // 수행할 함수
            int n = sc.nextInt(); // 배열에 들어잇는 원소의 개수
            String input = sc.next(); // [] 배열
            
            input = input.substring(1, input.length() - 1);
            String[] parts;
            
            if(input.equals("")) {
                parts = new String[0];
            } else {
                parts = input.split(",");
            }

            Deque<Integer> deque = new ArrayDeque<>();
            for (String s : parts) {
                deque.addLast(Integer.parseInt(s));
            }

            boolean isReverse = false;
            boolean isError = false;

            for(char c : p.toCharArray()) {
                if(c == 'R') {
                    isReverse = !isReverse;
                } else if(c == 'D') {
                    if(deque.isEmpty()) {
                        isError = true;
                        break;
                    }

                    if (!isReverse) {
                        deque.pollFirst();
                    } else {
                        deque.pollLast();
                    }
                }
            }



            if(isError) {
                System.out.println("error");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                if (!deque.isEmpty()) {
                    if (!isReverse) {
                        while (deque.size() > 1) {
                            sb.append(deque.pollFirst()).append(",");
                        }
                        sb.append(deque.pollFirst());
                    } else {
                        while (deque.size() > 1) {
                            sb.append(deque.pollLast()).append(",");
                        }
                        sb.append(deque.pollLast());
                    }
                }
                sb.append("]");
                System.out.println(sb);
            }
        }
    }
}
