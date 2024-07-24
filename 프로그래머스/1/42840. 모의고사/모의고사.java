import java.util.*;
class Solution {
    public static int[] solution(int[] answers) {
        //First Student
        int firstStudent = 1;
        int[] hits = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if (firstStudent == answers[i]) {
                hits[0]++;
            }
            firstStudent++;
            if (firstStudent == 6) {
                firstStudent = 1;
            }
        }
        int secondStudent = 1;
        for (int i = 0; i < answers.length; i++) {
            if (i % 2 == 0 && 2 == answers[i]) {
                hits[1]++;
            } else if (i % 2 != 0) {
                if (secondStudent == answers[i]) {
                    hits[1]++;
                }
                secondStudent++;
                if (secondStudent == 2) {
                    secondStudent++;
                } else if (secondStudent == 6) {
                    secondStudent = 1;
                }

            }
        }
        int thirdStudent = 3;
        int count = 0;
        boolean isFirst = true;

        // 세 번째 학생 패턴
        int[] thirdPattern = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == thirdPattern[i % thirdPattern.length]) {
                hits[2]++;
            }
        }

        // 가장 많이 맞힌 학생 찾기
        int maxHits = Math.max(hits[0], Math.max(hits[1], hits[2]));
        ArrayList<Integer> topStudents = new ArrayList<>();
        for (int i = 0; i < hits.length; i++) {
            if (hits[i] == maxHits) {
                topStudents.add(i + 1);
            }
        }

        // 리스트를 배열로 변환하여 반환
        return topStudents.stream().mapToInt(i -> i).toArray();
    }
}