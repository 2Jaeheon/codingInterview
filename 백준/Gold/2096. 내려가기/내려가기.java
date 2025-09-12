import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 첫 번째 줄에서 0, 1, 2에서 각각 시작했을 때의 최대, 최소값을 구하면 됨.
        // 따라서 함수를 세 번 호출하면 됨
        // 함수는 최대값 혹은 최소값을 가지고 오면 됨
        // 세가지 경우에서 각각 시작한다음 최대, 최소를 구해서 반환하면 되지 않을까?
        // 근데 DFS기반으로 하기에는 깊이가 너무 깊어져서 아마 시간 초과가 날 거 같아
        // DP를 기반으로 해서 하면 되지 않을까?
        // 만일 DP를 쓴다면 세가지 경로에서 시작하는게 아니라 그냥 최소랑 최대를 각각 저장해놓고 최적값으로 계속 갱신이 가능하지 않을까

        // 점화식을 세워야하는데 어떻게??
        // maxDP[i], minDP[i]이렇게 두 개 만들어서 그 값의 최솟값과 최댓값을 차례로 계속 갱신하면 되겠지?
        int[] maxDP = new int[3];
        int[] minDP = new int[3];

        int n = sc.nextInt();

        for(int i = 0; i < n; i++) {
            int a, b, c;
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();

            int tempA = a + Math.max(maxDP[0], maxDP[1]);
            int tempB = b + Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2]));
            int tempC = c + Math.max(maxDP[1], maxDP[2]);

            maxDP[0] = tempA;
            maxDP[1] = tempB;
            maxDP[2] = tempC;

            tempA = a + Math.min(minDP[0], minDP[1]);
            tempB = b + Math.min(minDP[0], Math.min(minDP[1], minDP[2]));
            tempC = c + Math.min(minDP[1], minDP[2]);

            minDP[0] = tempA;
            minDP[1] = tempB;
            minDP[2] = tempC;
        }

        int min = 100000000;
        int max = -1;
        
        for(int i = 0; i < 3; i++) {
            max = Math.max(max, maxDP[i]);
            min = Math.min(min, minDP[i]);
        }

        System.out.print(max + " " + min);
    }
}
