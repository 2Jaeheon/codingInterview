import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.next();
        String second = sc.next();
        String third = sc.next();

        // 원래 window를 둬서 해결하였으나 0.5초 시간에 걸림
        // FizzBuzz가 15개마다 정확하게 주기적으로 반복된다는 조건을 바탕으로 해결하려고 함.
        // 주어진 세 개의 입렵이 어디서 시작되는지만 알면 나머지는 다 계산 가능해짐

        // 15개를 돌면서 세 칸 패턴이 어디서 시작되는지를 체크함.
        for(int i = 0; i < 15; i++){
            boolean matched = true;
            long m = -1;

            // 3번 반복하면서 일치하는지를 체크함.
            for(int offset = 0; offset < 3; offset++){
                long index = i + offset;
                String expected = fizzBuzz(index);

                // input이 first, second, third중 하나로 선택됨.
                String input;
                if (offset == 0) {
                    input = first;
                } else if (offset == 1) {
                    input = second;
                } else {
                    input = third;
                }

                // 만약 input값이 문자열이고 다음과 같다면 
                if(input.equals("Fizz") || input.equals("Buzz") || input.equals("FizzBuzz")){
                    // 예측값이 다르다면 바로 break; 해서 다음 i로 넘어감
                    if(!expected.equals(input)){
                        matched = false;
                        break;
                    }
                } 
                
                else { // input이 숫자일 때
                    // number가 input값
                    // diff는 index를 뺀 것 (index는 3번 반복하면서 잡는 index)
                    long number = Long.parseLong(input);
                    long diff = number - index;
                    
                    // diff가 0보다 작거나 mod 15가 0이 아니면 match가 아닌 것
                    if(diff < 0 || diff % 15 != 0){
                        matched = false;
                        break;
                    }

                    // 현재 시작 오프셋 i 에서 m이 하나라도 정의되면
                    // 나머지 숫자 칸들도 동일한 m이어야 같은 “주기 반복”
                    long currentM = diff / 15;

                    // m이 -1이라면 아직 숫자 칸을 만나지 못한 것.
                    // 첫 숫자 칸에서는 m = currentM으로 저장
                    if(m == -1){
                        m = currentM;
                    } else if(m != currentM){ // 두 번째 칸부터는 currentM이 m이어야 함.
                        // 다르면 패턴이 깨지기 때문에 currentM이 m이어야 함.
                        matched = false;
                        break;
                    }
                }
            }

            // 매칭이 성공했다면 다음 번호를 가지고 fizzbuzz() 함수 씀.
            if(matched) {
                long next = i + 3 + 15 * (m == -1 ? 0 : m);
                System.out.println(fizzBuzz(next));
                return;
            }
        }
    }

    public static String fizzBuzz(long n){
        if(n % 15 == 0) {
            return "FizzBuzz";
        } else if (n % 3 == 0){
            return "Fizz";
        } else if (n % 5 == 0) {
            return "Buzz";
        }
        return Long.toString(n);
    }
}