import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // M개의 주사위가 있어서, i번째 주사위가 Ni 면체 주사위 
        // 모든 면에 적힌 수를 더한 값Si
        
        // a / b라면 a * b^-1 mod X 로 대신 계산
        // 결국 구해야하는 것은 N^-1.
        // => N ^ (MOD - 2)
        final long MOD = 1_000_000_007L;
        long M = sc.nextLong(); // 주사위 개수
        long answer = 0;
        
        for(int i = 0; i < M; i++) {
            long N = sc.nextLong();
            long S = sc.nextLong();
            
            long inv = 1;
            long power = MOD - 2; // 지수
            long base = N % MOD; // 밑

            // 구하고자 하는 값: inv = base ^ power mod MOD
            while (power > 0) {
                // 현재 power가 홀수라면, inv에 base를 한 번 곱해준다
                // 지수가 홀수면 inv * base
                if (power % 2 == 1) {
                    inv = (inv * base) % MOD;
                }
            
                // 매 단계마다 base를 제곱해서 다음 단계로 넘김
                base = (base * base) % MOD;
            
                // 지수를 절반으로 줄이기
                power = power / 2;
            }

            long value = (S % MOD) * inv % MOD;
            answer = (answer + value) % MOD;
        }

        System.out.println(answer);
    }
}
