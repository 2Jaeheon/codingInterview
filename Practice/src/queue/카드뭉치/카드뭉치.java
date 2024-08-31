package queue.카드뭉치;

import java.util.ArrayDeque;
import java.util.Arrays;

public class 카드뭉치 {

    public static void main(String[] args) {
        String[] card1_1 = new String[]{"i", "drink", "water"};
        String[] card1_2 = new String[]{"want", "to"};

        String[] card2_1 = new String[]{"i", "water", "drink"};
        String[] card2_2 = new String[]{"want", "to"};

        String[] goal = new String[]{"i", "want", "to", "drink", "water"};

        String[] card3_1 = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[] card3_2 = new String[]{"string", "or", "integer"};
        String[] goal2 = new String[]{"string", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        //yes

        System.out.println(solution(card1_1, card1_2, goal));
        System.out.println(solution(card2_1, card2_2, goal));
        System.out.println(solution(card3_1, card3_2, goal2));
    }

    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        int[] goal_num = new int[goal.length];
        int[] card1 = new int[cards1.length];
        int[] card2 = new int[cards2.length];

        for (int i = 0; i < card1.length; i++) {
            card1[i] = 10000000;
        }
        for (int i = 0; i < card2.length; i++) {
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

        System.out.println(Arrays.toString(card1));
        System.out.println(Arrays.toString(card1_clone));
        System.out.println(Arrays.toString(card2));
        System.out.println(Arrays.toString(card2_clone));

        if (Arrays.equals(card1, card1_clone) && Arrays.equals(card2, card2_clone)) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public String solution2(String[] cards1, String[] cards2, String[] goal) {
        // cards와 goal을 deque로 변환
        ArrayDeque<String> cardsDeque1 = new ArrayDeque<>(Arrays.asList(cards1));
        ArrayDeque<String> cardsDeque2 = new ArrayDeque<>(Arrays.asList(cards2));
        ArrayDeque<String> goalDeque = new ArrayDeque<>(Arrays.asList(goal));

        // ❶ goalDeque에 문자열이 남아있으면 계속 반복
        while (!goalDeque.isEmpty()) {
            // ❷ cardsDeque1의 front와 일치하는 경우
            if (!cardsDeque1.isEmpty() && cardsDeque1.peekFirst().equals(goalDeque.peekFirst())) {
                cardsDeque1.pollFirst();
                goalDeque.pollFirst();
            }
            // ❸ cardsDeque2의 front와 일치하는 경우
            else if (!cardsDeque2.isEmpty() && cardsDeque2.peekFirst()
                .equals(goalDeque.peekFirst())) {
                cardsDeque2.pollFirst();
                goalDeque.pollFirst();
            } else {
                break; // 일치하는 원소를 찾지 못했으므로 종료
            }
        }

        // ❹ goal이 비었으면 "Yes" 아니면 "No"를 반환
        return goalDeque.isEmpty() ? "Yes" : "No";
    }
}
