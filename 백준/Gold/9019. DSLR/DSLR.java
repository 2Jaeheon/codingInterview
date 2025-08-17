import java.io.*;
import java.util.*;

public class Main {
    static int D(int n) { return (2 * n) % 10000; }
    static int S(int n) { return (n + 9999) % 10000; }
    static int L(int n) { return (n % 1000) * 10 + (n / 1000); }
    static int R(int n) { return (n % 10) * 1000 + (n / 10); }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[10000];
            int[] prev = new int[10000];     // prev[x] = x로 오기 전 상태
            char[] op = new char[10000];     // op[x] = prev[x] -> x 에 사용된 연산
            Arrays.fill(prev, -1);

            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.offer(A);
            visited[A] = true;

            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur == B) break;

                int[] cand = { D(cur), S(cur), L(cur), R(cur) };
                char[] ops  = { 'D',     'S',     'L',     'R' };

                for (int i = 0; i < 4; i++) {
                    int nxt = cand[i];
                    if (!visited[nxt]) {
                        visited[nxt] = true;
                        prev[nxt] = cur; // 이전 상태가 뭐였는지를 저장
                        op[nxt] = ops[i]; // 어떤 연산자를 썼는지 저장
                        q.offer(nxt);
                    }
                }
            }

            // 역추적: B -> A
            StringBuilder path = new StringBuilder();
            for (int x = B; x != A; x = prev[x]) {
                path.append(op[x]);
            }
            out.append(path.reverse()).append('\n');
        }

        System.out.print(out.toString());
    }
}