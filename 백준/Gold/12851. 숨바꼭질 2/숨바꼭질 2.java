import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 걸을 때: 1초 후 x-1 or x+1로 이동
        // 순간이동 할 때: 1초 후 2*x 로 이동
        // 가장 빨리 도착하는 시간과, 몇 가지 방법이 있는지를 구해야함.

        // BFS로 하는 것이 가장 옳아보임
        int n = sc.nextInt(); // start
        int k = sc.nextInt(); // end

        Queue<Integer> queue = new ArrayDeque<>();
        int[] visited = new int[100001]; // 방문까지의 최소시간을 기록할 용도
        Arrays.fill(visited, -1);

        queue.offer(n);
        visited[n] = 0;

        int minTime = -1; // 가장 빠른 시간
        int count = 0;    // 방법의 수
        
        while(!queue.isEmpty()) {
            int curPosition = queue.poll();
            int curTime =  visited[curPosition];

            if(minTime != -1 && curTime > minTime) {
                break;
            }

            // 동생을 찾았을 때
            if(curPosition == k) {
                if(minTime == -1) {
                    minTime = curTime;
                }

                if(minTime == curTime) {
                    count++;
                }
            }

            
            int[] nextPositions = new int[]{curPosition - 1, curPosition + 1, curPosition * 2};
            for(int nextPosition : nextPositions) {
                if (nextPosition >= 0 && nextPosition <= 100_000) {
                    if(visited[nextPosition] == -1 || visited[nextPosition] == curTime + 1) {
                        visited[nextPosition] = curTime + 1;
                        queue.offer(nextPosition);
                    }
                }
            }
        }

        System.out.println(minTime);
        System.out.println(count);
    }
}
