import java.util.*;
class Solution {
    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        int[] goal_num = new int[goal.length];
        int[] card1 = new int[cards1.length];
        int[] card2 = new int[cards2.length];

        for(int i = 0; i < card1.length; i++){
            card1[i] = 10000000;
        }
        for(int i = 0; i < card2.length; i++){
            card2[i] = 10000000;
        }

        //큐의 구조는 먼저 넣은게 먼저 나오는 구조
        for (int i = 0; i < goal.length; i++) {
            goal_num[i] = i + 1;
        }

        for (int i = 0; i < goal.length; i++) {
            for (int j = 0; j < cards1.length; j++) {
                if (cards1[j].equals(goal[i])) {
                    card1[j] = i + 1;
                }
            }
        }

        for (int i = 0; i < goal.length; i++) {
            for (int j = 0; j < cards2.length; j++) {
                if (cards2[j].equals(goal[i])) {
                    card2[j] = i + 1;
                }
            }
        }

        int[] card1_clone = card1.clone();
        int[] card2_clone = card2.clone();
        Arrays.sort(card1_clone);
        Arrays.sort(card2_clone);


        if (Arrays.equals(card1, card1_clone) && Arrays.equals(card2, card2_clone)) {
            return "Yes";
        } else {
            return "No";
        }
    }
}