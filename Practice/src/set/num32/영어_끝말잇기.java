package set.num32;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 영어_끝말잇기 {

    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 5;
        int n3 = 2;

        String[] words1 = new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother",
            "robot", "tank"};
        String[] words2 = new String[]{"hello", "observe", "effect", "take", "either", "recognize",
            "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate",
            "executive"};
        String[] words3 = new String[]{"hello", "one", "even", "never", "now", "world", "draw"};

        System.out.println(Arrays.toString(solution(n1, words1)));
        System.out.println(Arrays.toString(solution(n2, words2)));
        System.out.println(Arrays.toString(solution(n3, words3)));
    }

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

    public static int[] solution2(int n, String[] words) {
        HashSet<String> usedWords = new HashSet<>();
        char prevWord = words[0].charAt(0);

        for (int i = 0; i < words.length; i++) {
            if (usedWords.contains(words[i]) || words[i].charAt(0) != prevWord) {
                return new int[]{(i % n) + 1, (i / n) + 1};
            }
            usedWords.add(words[i]);
            prevWord = words[i].charAt(words[i].length() - 1);
        }

        return new int[]{0, 0};
    }
}
