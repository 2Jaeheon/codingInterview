package Array;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class 배열_데이터_정렬 {

    public static void main(String[] args) {
        int[] a = new int[]{2, 1, 3, 4, 1};
        int[] b = new int[]{5, 0, 2, 7};

        System.out.println(Arrays.toString(solution(a)));
        System.out.println(Arrays.toString(solution(b)));

        System.out.println(Arrays.toString(solution2(a)));
        System.out.println(Arrays.toString(solution2(b)));
    }

    private static int[] solution(int[] arr) {
        Integer[] result = Arrays.stream(arr)
            .boxed()
            .distinct()
            .toArray(Integer[]::new);
        Arrays.sort(result, Collections.reverseOrder());
        return Arrays.stream(result)
            .mapToInt(Integer::intValue)
            .toArray();
    }


    private static int[] solution2(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        for (int num : arr) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = set.pollFirst();
        }
        return result;
    }

}
