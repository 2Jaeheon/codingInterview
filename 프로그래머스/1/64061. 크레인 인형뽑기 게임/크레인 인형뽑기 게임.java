import java.util.*;
class Solution {
    public static int solution(int[][] board, int[] moves) {
        int n = board.length;
        int count = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        ArrayDeque[] game = new ArrayDeque[n];

        for (int i = n - 1; i >= 0; i--) {
            game[i] = new ArrayDeque<>();
            for (int j = n - 1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    game[i].push(board[j][i]);
                }

            }
        }

        for (int i = 0; i < moves.length; i++) {
            //스택이 비어있을 때
            if (stack.isEmpty()) {
                if (!game[moves[i] - 1].isEmpty()) {
                    stack.push((Integer) game[moves[i] - 1].pop());
                }
            } else { //스택이 차있을 때
                if (stack.peek() == game[moves[i] - 1].peek()) {
                    game[moves[i]-1].pop();
                    stack.pop();
                    count += 2;
                } else {
                    if (!game[moves[i] - 1].isEmpty()) {
                        stack.push((Integer) game[moves[i] - 1].pop());
                    }
                }
            }
        }

        for (int i = 0; i < game.length; i++) {
            System.out.println(game[i]);
        }
        return count;
    }
}