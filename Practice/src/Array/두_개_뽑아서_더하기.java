package Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class 두_개_뽑아서_더하기 {

    static int count = 0;
    static int count2 = 0;

    public static void main(String[] args) {
        int[] a = new int[]{2, 1, 3, 4, 1};
        int[] b = new int[]{5, 0, 2, 7};

        System.out.println(Arrays.toString(solution(a)));
        System.out.println(Arrays.toString(solution(b)));

        System.out.println(Arrays.toString(solution2(a)));
        System.out.println(Arrays.toString(solution2(b)));
    }

    //내가 푼 풀이
    private static int[] solution(int[] numbers) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (i != j) {
                    set.add(numbers[i] + numbers[j]);
                    count++;
                }
            }
        }

        int[] result = set.stream()
            .mapToInt(Integer::intValue)
            .toArray();

        return result;
    }

    //책에서 제시한 풀이
    private static int[] solution2(int[] numbers) {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (i != j) {
                    set.add(numbers[i] + numbers[j]);
                    count2++;
                }
            }
        }

        int[] result = set.stream()
            .mapToInt(Integer::intValue)
            .sorted()
            .toArray();

        return result;
    }
}
