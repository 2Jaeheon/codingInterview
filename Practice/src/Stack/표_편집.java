package Stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class 표_편집 {

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        String[] cmd2 = new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1",
            "C"};
        String[] cmd3 = new String[]{"C", "C", "D 3", "C", "Z"};
        System.out.println(solution2(n, k, cmd));
        System.out.println(solution2(n, k, cmd2) + "\n\n");
        System.out.println(solution2(n, k, cmd3) + "\n\n");
    }

    static class Fair {

        int index;
        int value;

        Fair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static String solution(int n, int k, String[] cmd) {
        int choice = k;
        int[] index = new int[n];
        List<Integer> list = new ArrayList<>();
        StringBuilder answer = new StringBuilder();
        ArrayDeque<Fair> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        for (int i = 0; i < cmd.length; i++) {
            char action = cmd[i].charAt(0);
            if (action == 'U') {
                int upNum = Integer.parseInt(cmd[i].split(" ")[1]);
                choice -= upNum;
            } else if (action == 'D') {
                int downNum = Integer.parseInt(cmd[i].split(" ")[1]);
                choice += downNum;
            } else if (action == 'C') {
                stack.push(new Fair(choice, list.get(choice)));
                list.remove(choice);
                if (choice >= list.size()) {
                    choice = list.size() - 1;
                }
            } else if (action == 'Z') {
                Fair x = stack.pop();
                list.add(x.index, x.value);
                if (x.index <= choice) {
                    choice++;
                }

            }
        }

        for (int i = 0; i < n; i++) {
            if (list.contains(i)) {
                answer.append('O');
            } else {
                answer.append('X');
            }
        }

        return answer.toString();
    }

    public static String solution2(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        Stack<Integer> removed = new Stack<>();
        int current = k;

        for (String command : cmd) {
            char action = command.charAt(0);
            if (action == 'U' || action == 'D') {
                int x = Integer.parseInt(command.substring(2));
                if (action == 'U') {
                    while (x-- > 0) {
                        current = prev[current];
                    }
                } else {
                    while (x-- > 0) {
                        current = next[current];
                    }
                }
            } else if (action == 'C') {
                System.out.println(Arrays.toString(next));
                System.out.println(Arrays.toString(prev));
                System.out.println();

                removed.push(current);
                System.out.println(current);

                if (prev[current] != -1) {
                    next[prev[current]] = next[current];
                }

                if (next[current] != -1) {
                    prev[next[current]] = prev[current];
                }

                current = next[current] != -1 ? next[current] : prev[current];

            } else if (action == 'Z') {
                int restore = removed.pop();
                if (prev[restore] != -1) {
                    next[prev[restore]] = restore;
                }
                if (next[restore] != -1) {
                    prev[next[restore]] = restore;
                }
            }
        }

        char[] result = new char[n];
        Arrays.fill(result, 'X');
        for (int i = 0; i < n; i++) {
            if (prev[i] != -1 || next[i] != -1) {
                result[i] = 'O';
            }
        }
        for (int r : removed) {
            result[r] = 'X';
        }

        return new String(result);

    }

    public String solution3(int n, int k, String[] cmd) {
        //삭제된 행의 인덱스를 저장하는 스택
        Stack<Integer> deleted = new Stack<>();

        //각 행을 기준으로 연산에 따른 위치를 표시하기 위한 배열
        int[] up = new int[n + 2];
        int[] down = new int[n + 2];

        for (int i = 0; i < n + 2; i++) {
            up[i] = i - 1;
            down[i] = i + 1;
        }

        //현재 위치를 나타내는 인덱스
        k++;

        //주어진 명령어를 하나씩 처리
        for (String c : cmd) {
            //현재 위치를 삭제하고 그 다음 위치로 이동
            if (c.startsWith("C")) {
                deleted.push(k);
                up[down[k]] = up[k];
                down[up[k]] = down[k];

                if (n < down[k]) {
                    k = up[k];
                } else {
                    k = down[k];
                }
            }
            //가장 최근에 삭제한 행을 복원
            else if (c.startsWith("Z")) {
                int restore = deleted.pop();
                down[up[restore]] = restore;
                up[down[restore]] = restore;
            } else {
                String[] s = c.split(" ");
                int x = Integer.parseInt(s[1]);

                for (int i = 0; i < x; i++) {
                    k = s[0].equals("U") ? up[k] : down[k];
                }
            }
        }

        //삭제한 행의 위치에 'X'를, 그렇지 않은 행 위치에는 'O' 저장
        char[] answer = new char[n];
        Arrays.fill(answer, 'O');

        for (int i : deleted) {
            answer[i - 1] = 'X';
        }
        return new String(answer);
    }
}