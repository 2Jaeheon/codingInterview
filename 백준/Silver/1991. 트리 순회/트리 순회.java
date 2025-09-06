import java.util.*;

public class Main{
    static int[] left = new int[26];
    static int[] right = new int[26];

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Arrays.fill(left, -1);
        Arrays.fill(right, -1);


        for(int i = 0; i < n; i++) {
            char parent = sc.next().charAt(0);
            char l = sc.next().charAt(0);
            char r = sc.next().charAt(0);

            int parentIndex = parent - 'A';
            if(l != '.') {
                left[parentIndex] = l - 'A';
            }

            if(r != '.') {
                right[parentIndex] = r - 'A';
            }
        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
        System.out.println();
    }

    static void preOrder(int cur) {
        if(cur == -1) {
            return;
        }
        
        System.out.print((char)(cur + 'A'));
        preOrder(left[cur]);
        preOrder(right[cur]);
    }

    static void inOrder(int cur) {
       if(cur == -1) {
            return;
        }

        inOrder(left[cur]);
        System.out.print((char)(cur + 'A'));
        inOrder(right[cur]);
    }

    static void postOrder(int cur) {
        if(cur == -1) {
            return;
        }

        postOrder(left[cur]);
        postOrder(right[cur]);
        System.out.print((char)(cur + 'A'));
    }
}
