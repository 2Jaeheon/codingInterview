import java.util.*;
import java.io.*;

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        // Arrays.sort(arr);
        StringBuilder sb = new StringBuilder(n * 3);
        
        // 정석적으로 정렬해보자!
        // 힙정렬 해보자!

        heapSort(arr);

        for (int x : arr) {
            sb.append(x).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();   
    }

    // heapSort(최대 힙)
 
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // 힘을 구축하는 단계임.
        // 0까지 내려가면서 sift로직을 호출
        // sift는 자식노드와 비교해서 가능하다면 swap
        for(int i = n / 2 - 1; i >= 0; i--) {
            down(arr, i, n);
        }

        // 힙을 정렬하는 단계
        // 루트와 리프노드를 바꿔서
        // 리프노드를 루트부터 다시 최대 힙 구조를 맞춰줌.
        for(int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            down(arr, 0, i);
        }
    }

    // 최대 힙 구조를 만들어주는 로직
    public static void down(int[] arr, int i, int heapSize) {
        while(true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            // 왼쪽 자식과 오른쪽 자식중 더 큰 걸 선택해서
            // 부모로 올려주는 역할을 한다.      
            if(left < heapSize && arr[left] > arr[largest]) {
                largest = left;
            }
            if(right < heapSize && arr[right] > arr[largest]) {
                largest = right;
            }

            if(largest == i) {
                break;
            }

            // swap
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            i = largest;
        }
    }
}
