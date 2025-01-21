package set.num31;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class 폰켓몬 {

    public static int solution(int[] nums) {
        int selectableNum = nums.length / 2;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        if (set.size() >= selectableNum) {
            return selectableNum;
        } else {
            return set.size();
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 1, 2, 3}));
        System.out.println(solution(new int[]{3, 3, 3, 2, 2, 4}));
        System.out.println(solution(new int[]{3, 3, 3, 2, 2, 2}));
    }

    public static int solution2(int[] nums) {
        HashSet<Integer> set = Arrays.stream(nums).boxed()
            .collect(Collectors.toCollection(HashSet::new));

        int n = nums.length;
        int k = n / 2;

        return Math.min(k, set.size());
    }
}
