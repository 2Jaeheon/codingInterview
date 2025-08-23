import java.util.*;

public class Main{

    static int n, m;
    static int[] arr, sequence;
    static boolean[] visited;
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        // N개의 자연수 중에서 M개를 고른 수열
        arr = new int[n];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        sequence = new int[m];
        visited = new boolean[n];
        
        // 이제부터 배열의 맨 앞부터 DFS를 실행
        for (int i = 0; i < n; i++) {
            visited[i] = true; // 방문처리
            sequence[0] = arr[i]; // 첫자리를 고정
            dfs(1); // 나머지 자리를 채우기
            visited[i] = false; // 복원
        }
    }

    public static void dfs(int depth) {
        // pruning
        if(depth == m) {
            for(int answer : sequence) {
                System.out.print(answer + " ");
            }
            System.out.println();
            return ;
        }

        for(int i = 0; i < n; i++) {
            if(visited[i]) {
                // 이미 방문한건 다시 방문하지 않아야 함.
                continue;
            }

            visited[i] = true;
            sequence[depth] = arr[i]; // depth 번째를 채움
            dfs(depth+1);
            visited[i] = false;
        }
    }
}
