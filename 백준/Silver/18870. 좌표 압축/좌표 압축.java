import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] tempArr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] sorted = Arrays.copyOf(arr, n);
        Arrays.sort(sorted);
        
        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for(int i = 0; i < n; i++) {
            if (!map.containsKey(sorted[i])) {
                map.put(sorted[i], idx++);
            }
        }

        for (int i = 0; i < n; i++) {
            bw.write(map.get(arr[i]) + " ");
        }
        bw.flush();
    }
}