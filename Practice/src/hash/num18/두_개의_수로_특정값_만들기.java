package hash.num18;

import java.util.HashSet;
import java.util.Set;

public class 두_개의_수로_특정값_만들기 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 8};
        int target = 6;

        int[] arr2 = {2, 3, 5, 9};
        int target2 = 10;

        System.out.println(solution2(arr, target));
        System.out.println(solution2(arr2, target2));
    }

    public static boolean solution(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                set.add(arr[i] + arr[j]);
            }
        }

        if (set.contains(target)) {
            return true;
        }
        return false;
    }

    public static boolean solution2(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();


        // 1, 2, 3, 4, 8 target==6

        //특정 원소를 기준으로 그 원소를 타겟에서 뺀 값이 존재하는지를 확인하기만 하면 됨 => 해시가 효율적
        for (int i : arr) {
            if (set.contains(target - i)) {
                return true;
            }
            set.add(i);
        }
        return false;
    }

}
