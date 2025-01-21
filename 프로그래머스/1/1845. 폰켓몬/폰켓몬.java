import java.util.*;
class Solution {
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
}