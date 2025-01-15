package stack;

import java.util.ArrayDeque;

public class 크레인_인형뽑기_게임 {

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2},
            {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};

        System.out.println(solution2(board, moves));
    }

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
                    if (!game[moves[i] - 1].isEmpty()) {
                        game[moves[i] - 1].pop();
                    }
                    stack.pop();
                    count++;
                } else {
                    if (!game[moves[i] - 1].isEmpty()) {
                        stack.push((Integer) game[moves[i] - 1].pop());
                    }
                }
            }
        }

        return count * 2;
    }

    public static int solution2(int[][] board, int[] moves) {
        //각 열에 대한 스택 생성
        ArrayDeque[] lanes = new ArrayDeque[board.length];
        for (int i = 0; i < lanes.length; i++) {
            lanes[i] = new ArrayDeque<Integer>();
        }

        //board를 역순으로 탐색하면서 각 열의 인형을 lane에 추가
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = board.length - 1; j >= 0; j--) {
                if (board[i][j] > 0) {
                    lanes[j].push(board[i][j]);
                }
            }
        }

        //인형 담을 버킷 생성
        ArrayDeque<Integer> bucket = new ArrayDeque<>();
        //사라진 인형 개수 기록
        int answer = 0;

        for (int move : moves) {
            if (!lanes[move - 1].isEmpty()) {
                int doll = (Integer) lanes[move - 1].pop();
                if (!bucket.isEmpty() && bucket.peek() == doll) {
                    bucket.pop();
                    answer += 2;
                } else {
                    bucket.push(doll);
                }
            }
        }



        return answer;
    }

}
