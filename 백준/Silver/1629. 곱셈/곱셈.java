import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 자연수 A를 B번 곱한 수를 알고싶은데 C로 나눈 나머지를 구하는 프로그램
        long A = sc.nextInt();
        long B = sc.nextInt();
        long C = sc.nextInt();

        long result = 1;
        long base = A % C;
        
        // Math.pow()를 쓰면 double로 계산해서 오차가 커져버림
        // 우리가 구하고자하는 값은 거듭제곱한 값이 아닌 나머지임
        // 지수가 짝수면 절반만 계산해서 제곱
        // 지수가 홀수면 짝수로 맞춘 뒤 A로 조정

        // A^B에서 B를 2진수로 바꾸었을 때 1인 경우만 곱하면 됨.
        while(B > 0) {
            if(B % 2 == 1) { // 홀수일 때
                result = (result * base) % C;
            }
            
            // 다음 단계에 곱해질 값
            base = (base * base) % C; // 밑을 제곱해서 업데이트
            B = B / 2; // 지수를 절반으로 줄임
        }

        System.out.println(result);
    }
}
