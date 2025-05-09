import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {

    public static void main(String[] args) {
        String text = "ababcababababababcbabcbacbab";
        String pattern = "ababa";

        List<Integer> matches = KMPSearch(text, pattern);

        if (matches.isEmpty()) {
            System.out.println("패턴이 텍스트에 존재하지 않습니다.");
        } else {
            System.out.println("패턴이 일치하는 위치: ");
            for (int idx : matches) {
                System.out.println("인덱스 " + idx);
            }
        }
    }

    public static int[] computeLPS(String pattern) {
        // LPS == 비교 실패했을 때, 패턴의 어디부터 다시 비교를 시작할지를 알려주는 정보
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        System.out.println("LPS 배열은 다음과 같다: " + Arrays.toString(lps));
        return lps;
    }

    public static List<Integer> KMPSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        List<Integer> result = new ArrayList<>();
        int[] lps = computeLPS(pattern);

        int i = 0;
        int j = 0;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            // 패턴의 끝까지 도달했다는 것은 전체 패턴이 일치한 경우
            if (j == m) {
                result.add(i - j);
                j = lps[j - 1];

            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                // 문자가 일치하지 않는 경우
                if (j != 0) {
                    // 이전까지 일치한 접두사 정보를 활용해 j 포인터를 점프
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return result;
    }
}
