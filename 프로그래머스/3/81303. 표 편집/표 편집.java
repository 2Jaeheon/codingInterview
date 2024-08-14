import java.util.*;
class Solution {
   public String solution(int n, int k, String[] cmd) {
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
                    while (x-- > 0) current = prev[current];
                } else {
                    while (x-- > 0) current = next[current];
                }
            } else if (action == 'C') {
                removed.push(current);
                if (prev[current] != -1) next[prev[current]] = next[current];
                if (next[current] != -1) prev[next[current]] = prev[current];
                current = next[current] != -1 ? next[current] : prev[current];
            } else if (action == 'Z') {
                int restore = removed.pop();
                if (prev[restore] != -1) next[prev[restore]] = restore;
                if (next[restore] != -1) prev[next[restore]] = restore;
            }
        }

        char[] result = new char[n];
        Arrays.fill(result, 'X');
        for (int i = 0; i < n; i++) {
            if (prev[i] != -1 || next[i] != -1) result[i] = 'O';
        }
        for (int r : removed) {
            result[r] = 'X';
        }

        return new String(result);
    }
}