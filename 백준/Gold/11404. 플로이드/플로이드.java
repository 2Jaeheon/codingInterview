    import java.util.*;
    import java.io.*;

    public class Main{
        public static void main(String args[]) throws IOException{
            Scanner sc = new Scanner(System.in);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());

            int[][] dist = new int[n + 1][n + 1];
            
            for(int i = 0; i <= n; i++) {
                Arrays.fill(dist[i], 100_000_000);
            }
            
            for(int i = 0; i <= n; i++) {
                dist[i][i] = 0;
            }
            
            for(int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                
                if(dist[from][to] > weight) {
                    dist[from][to] = weight;
                }
            }
                
            for(int k = 1; k <= n; k++) { // 경유지
                for(int i = 1; i <= n; i++) { // 시작
                    for(int j = 1; j <= n; j++) { // 도착
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }

            
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(dist[i][j] == 100_000_000) {
                        System.out.print(0 + " ");
                    } else {
                        System.out.print(dist[i][j] + " ");
                    }
                }
                System.out.println();
            }
        }
    }
