import java.util.*;

public class Main{

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    static class Point {
        int x;
        int y;
        int type;

        public Point(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] board = new int[n][m];
        
        List<Point> emptyCells = new ArrayList<>();
        List<Point> virusCells = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                board[i][j] = sc.nextInt();

                if(board[i][j] == 0) {
                    emptyCells.add(new Point(i, j, 0));
                } else if(board[i][j] == 2) {
                    virusCells.add(new Point(i, j, 2));
                }
            }
        }
        

        // 안전영역의 최대 크기를 출력해야함.
        // 벽을 꼭 세개 세워야함.
        // 어떻게 벽을 세 개를 쳐 바이러스가 퍼지는걸 억제할 수 있지?

        // 경우의 수를 따져봐야하나?
        // 일단 그러면 세 가지 벽을 쳐서 바이러스를 퍼트려보고 안전영역의 수를 계속 갱신시켜보자
        
        int maxSafeArea = 0; // 최댓값을 저장할 변수
        int emptySize = emptyCells.size();
        // 이제 벽을 한 번 쳐보자
        for(int i = 0; i < emptySize; i++) {
            for(int j = i + 1; j < emptySize; j++) {
                for(int k = j + 1; k < emptySize; k++) {

                    // 시뮬레이션을 위한 지도 복사
                    int[][] tempBoard = boardCopy(board);
                    
                    // 세 개의 벽을 세우기
                    Point wall1 = emptyCells.get(i);
                    Point wall2 = emptyCells.get(j);
                    Point wall3 = emptyCells.get(k);

                    tempBoard[wall1.x][wall1.y] = 1;
                    tempBoard[wall2.x][wall2.y] = 1;
                    tempBoard[wall3.x][wall3.y] = 1;

                    // 바이러스 퍼트리기
                    spreadVirus(tempBoard, virusCells);

                    int currentSafeArea = 0;

                    for(int row = 0; row < n; row++) {
                        for(int col = 0; col < m; col++) {
                            if (tempBoard[row][col] == 0) {
                                currentSafeArea++;
                            }
                        }
                    }

                    maxSafeArea = Math.max(maxSafeArea, currentSafeArea);
                }
            }
        }
        System.out.println(maxSafeArea);
    }

    public static void spreadVirus(int[][] board, List<Point> virusCells) {
        int n = board.length;
        int m = board[0].length;
        Queue<Point> queue = new ArrayDeque<>();
        
        for(Point virus : virusCells) {
            queue.offer(virus);
        }

        while(!queue.isEmpty()) {
            Point cur = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if(nextX >= 0 && nextY >= 0 && nextX < n && nextY < m) {
                    if(board[nextX][nextY] == 0) {
                        board[nextX][nextY] = 2;
                        queue.offer(new Point(nextX, nextY, 2));
                    }
                }
            }
        }
    }

    public static int[][] boardCopy(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        
        int[][] newBoard = new int[n][m];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        return newBoard;
    }
}
