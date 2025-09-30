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

        queue.add(n);
        visited[n] = 0;

        int minTime = -1; // 가장 빠른 시간
        int count = 0;    // 방법의 수
        
        while(!queue.isEmpty()) {
            int currentPosition = queue.poll();
            int currentTime = visited[currentPosition];

            // 이미 찾은 가장 빠른 시간보다 현재 시간이 더 오래 걸리면 더 이상 탐색할 필요가 없음
            if(minTime != -1 && currentTime > minTime) {
                break;
            }

            // 동생을 찾았을 때
            if (currentPosition == k) {
                // 처음 찾은 경우
                if (minTime == -1) {
                    minTime = currentTime;
                }
                // 가장 빠른 시간과 동일한 시간에 도착한 경우, 방법의 수 증가
                if (minTime == currentTime) {
                    count++;
                }
            }
            
            int[] nextPositions = {currentPosition - 1, currentPosition + 1, currentPosition * 2};

            for (int nextPos : nextPositions) {
                // 위치가 유효한 범위 내에 있는지 확인
                if (nextPos >= 0 && nextPos <= 100_000) {
                    if (visited[nextPos] == -1 || visited[nextPos] == currentTime + 1) {
                        visited[nextPos] = currentTime + 1;
                        queue.add(nextPos);
                    }
                }
            }
        }

        System.out.println(minTime);
        System.out.println(count);
    }
}
