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

            
            for(int offset = 0; offset < 3; offset++){
                long index = i + offset;
                String expected = fizzBuzz(index);

                String input = offset == 0 ? first : (offset == 1 ? second : third);

                if(input.equals("Fizz") || input.equals("Buzz") || input.equals("FizzBuzz")){
                    if(!expected.equals(input)){
                        matched = false;
                        break;
                    }
                } else {
                    // input이 숫자라면, index + 15 * m(주기)
                    long number = Long.parseLong(input);
                    long diff = number - index;

                    if(diff < 0 || diff % 15 != 0){
                        matched = false;
                        break;
                    }

                    long currentM = diff / 15;

                    if(m == -1){
                        m = currentM;
                    } else if(m != currentM){
                        matched = false;
                        break;
                    }
                }
            }

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