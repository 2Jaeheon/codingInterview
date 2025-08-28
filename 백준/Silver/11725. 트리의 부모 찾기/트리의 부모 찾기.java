import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 루트 없는 트리 -> 트리의 루트는 1
        // 각 노드의 부모를 구하는 프로그램

        int n = sc.nextInt();
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for(int i = 0; i < n - 1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            map.get(x).add(y);
            map.get(y).add(x);
        }

        int[] answer = new int[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int next : map.get(cur)) {
                if(answer[next] == 0) {
                    answer[next] = cur;
                    queue.offer(next);
                }
            }
        }

        for(int i = 2; i <= n; i++) {
            System.out.println(answer[i]);
        }
    }
}
