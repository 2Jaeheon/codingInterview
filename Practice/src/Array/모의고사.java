package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;


public class 모의고사 {

    public static void main(String[] args) {
        int[] x = new int[]{3, 4, 2, 4, 2, 4, 2, 5, 2, 1};
        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(solution2(x)));
    }

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

        //third Student
        int[] thirdPattern = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == thirdPattern[i % thirdPattern.length]) {
                hits[2]++;
            }
        }

        // 가장 많이 맞힌 학생 찾기
        //int maxHits = Math.max(hits[0], Math.max(hits[1], hits[2]));
        int maxHits = Arrays.stream(hits).max().getAsInt();
        ArrayList<Integer> topStudents = new ArrayList<>();
        for (int i = 0; i < hits.length; i++) {
            if (hits[i] == maxHits) {
                topStudents.add(i + 1);
            }
        }

        // 리스트를 배열로 변환하여 반환
        return topStudents.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] solution2(int[] answers) {
        int[][] pattern = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        int[] scores = new int[3];

        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                if (answers[i] == pattern[j][i % pattern[j].length]) {
                    scores[j]++;
                }
            }
        }

        int maxScore = Arrays.stream(scores).max().getAsInt();
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == maxScore) {
                answer.add(i + 1);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}