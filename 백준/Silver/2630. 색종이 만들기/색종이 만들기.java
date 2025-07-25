import java.util.*;

public class Main {
    static int whiteCount = 0;
    static int blueCount = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] papers = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                papers[i][j] = sc.nextInt();
            }
        }

        Block root = new Block(papers);
        divideAndCount(root);

        System.out.println(whiteCount);
        System.out.println(blueCount);
    }

    public static class Block {
        int[][] papers;

        public Block(int[][] array) {
            this.papers = array;
        }

        // 블럭이 하나의 색으로 되어있는지 판별하는 함수
        public boolean isMonochrome() {
            int color = papers[0][0];

            for(int i = 0; i < papers.length; i++) {
                for(int j = 0; j < papers[i].length; j++) {
                    if(papers[i][j] != color) {
                        return false;
                    }
                }
            }

            return true;
        }

        public Block[] split() {
            int n = papers.length;
            int half = n / 2;
            Block[] children = new Block[4];

            int[][] topLeft = new int[half][half];
            int[][] topRight = new int[half][half];
            int[][] bottomLeft = new int[half][half];
            int[][] bottomRight = new int[half][half];

            // 실제값 복사
            for (int i = 0; i < half; i++) {
                for (int j = 0; j < half; j++) {
                    topLeft[i][j] = papers[i][j];                       // I
                    topRight[i][j] = papers[i][j + half];               // II
                    bottomLeft[i][j] = papers[i + half][j];             // III
                    bottomRight[i][j] = papers[i + half][j + half];     // IV
                }
            }

            // block 객체 생성
            children[0] = new Block(topLeft);
            children[1] = new Block(topRight);
            children[2] = new Block(bottomLeft);
            children[3] = new Block(bottomRight);

            return children;
        }
    }

    public static void divideAndCount(Block block) {
        if(block.isMonochrome()) {
            int color = block.papers[0][0];
            if(color == 0) {
                whiteCount++;
            } else {
                blueCount++;
            }
        } else {
            for(Block child : block.split()) {
                divideAndCount(child);
            }
        }
    }
}