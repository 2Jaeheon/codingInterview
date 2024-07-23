import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        //TreeSet을 사용하는것과 HashSet을 사용해서 sorted() 쓰는 것과 동일한 시간복잡도를 보임
        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i < numbers.length - 1; i++){
            for(int j = i + 1; j < numbers.length; j++){
                set.add(numbers[i] + numbers[j]);
            }
        }
        
        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}