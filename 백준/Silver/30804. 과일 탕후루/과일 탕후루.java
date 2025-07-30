import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        // 앞에서 a개를 빼고, 뒤에서 b개를 뺌
        // N - (a + b) 개가 남은 탕후루가 됨
        // 이렇게 만들 수 있는 탕후루 중 과일의 개수가 가장 많은 탕후루의 과일 개수

        // 앞과 뒤에서 하나씩 빼보면서 맵의 사이트가 2인지 확인하면 되지 않을까?
        Map<Integer, Integer> map = new HashMap<>();
        int[] fruits = new int[n];
        for(int i = 0; i < n; i++) {
            fruits[i] = sc.nextInt();
        }

        int left = 0;
        int answer = 0;

        // 오른쪽 포인터를 계속 이동시키면서 
        // 필요시 왼쪽을 이동
        for(int right = 0; right < n; right++) {
            // 오른쪽걸 하나를 빼옴
            int fruit = fruits[right];
            map.put(fruit, map.getOrDefault(fruit, 0) + 1);

            // map에 들어있는 종류가 2개 이상이다? -> 왼쪽을 추가
            while(map.size() > 2) {
                int leftFruit = fruits[left];
                map.put(leftFruit, map.get(leftFruit) - 1);

                // leftFruit가 들어있는 개수가 0이라면 맵에서 제거
                if(map.get(leftFruit) == 0) {
                    map.remove(leftFruit);
                }
                left++;
            }

            // map의 사이즈가 2 이하인 것
            // 이는 종류가 2 이하라는 뜻 => answer를 갱신
            answer = Math.max(answer, right - left + 1);
        }

        System.out.println(answer);
    }
}