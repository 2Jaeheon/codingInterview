import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        // 기본 관점은 n에서 제곱수를 계속 빼서 0에 최대한 빨리 도달하는 걸 고르면 됨.
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{n, 0});
        // 첫 번째 원소는 남은개수
        // 두 번째 원소는 몇 번 뺐는지 카운트
        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        while(!queue.isEmpty()) {
            int[] temp = queue.poll();
            // 남은 개수와 카운트
            int x = temp[0];
            int count = temp[1];

            for(int i = (int)Math.sqrt(x); i >= 1; i--) {
                int next = x - (i * i);

                if(next == 0) {
                    System.out.println(count + 1);
                    return ;
                }

                if(!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, count + 1});
                }
            }
        }
    }
}