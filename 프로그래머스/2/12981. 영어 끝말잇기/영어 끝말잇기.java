import java.util.*;
class Solution {
    public static int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();
        int[] answer = new int[2];
        set.add(words[0]);
        int human = 2; // 몇 번째 사람
        int cycle = 1; // 몇 번째 차례
        int count = 1;

        // 두 번째 사람부터 시작
        for (int i = 1; i < words.length; i++) {
            char previousWordsAlphabet = words[i - 1].charAt(words[i - 1].length() - 1);
            char currentWordsAlphabet = words[i].charAt(0);

            // n번마다 1 증가
            if (count++ == n) {
                count = 1;
                cycle++;
            }

            // n + 1번째 사람이 될 때마다 1로 초기화
            if (human == n + 1) {
                human = 1;
            }

            if (!set.contains(words[i])
                && previousWordsAlphabet == currentWordsAlphabet) {
                set.add(words[i]);
            } else {
                answer[0] = human;
                answer[1] = cycle;
                break;
            }

            human++;
        }

        return answer;
    }
}