import java.util.*;

public class Main{

    static int N, M;
    static ArrayList<Point> houses = new ArrayList<>(); // 집 좌표 저장 리스트
    static ArrayList<Point> chickens = new ArrayList<>(); // 치킨집 좌표 저장 리스트
    static Point[] selectedChickens; // 선택된 M개 치킨집을 담을 배열
    static int minCityChickenDist = Integer.MAX_VALUE; // 도시 치킨 거리의 최솟값
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 도시의 각 칸은 집, 치킨집, 빈 칸 중 하나
        // 치킨거리: 집과 가장 가까운 치킨집 사이의 거리
        // 치킨 거리는 집을 기준으로 정해지고 각각의 집은 치킨 거리가 가짐
        // 도시의 치킨거리: 모든 집의 치킨거리 합

        // 거리: |r1 - r2| + |c1 - c2|
        // 0: 빈칸, 1: 집, 2: 치킨집

        // 이 도시의 가장 수익을 많이 낼 수 있는 치킨집의 개수 M
        // M개까지만 고르고 나머지는 폐업
        // 도시의 치킨거리의 최솟값

        N = sc.nextInt();
        M = sc.nextInt();
        selectedChickens = new Point[M]; 

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int input = sc.nextInt();
                if (input == 1) {
                    houses.add(new Point(i, j)); // 집 좌표 저장
                } else if (input == 2) {
                    chickens.add(new Point(i, j)); // 치킨집 좌표 저장
                }
            }
        }
        

        // 0번째 치킨집부터 현재까지 0개 선택함
        find(0, 0);
        System.out.println(minCityChickenDist);
    }

    public static void find(int start, int count) {
        // 치킨집이 모두 선택됐다면
        if(count == M) {
            int curCityDist = 0;

            // 현재 선택된 치킨집 조합으로 도시의 치킨 거리를 계산
            for(Point house : houses) {
                int houseDist = Integer.MAX_VALUE;

                for(Point chicken : selectedChickens) {
                    // 거리 계산
                    int dist = Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c);
                    // 집 거리 = houstDist와 dist의 최소값
                    houseDist = Math.min(houseDist, dist);
                }
                
                curCityDist += houseDist;
            }

            minCityChickenDist = Math.min(minCityChickenDist, curCityDist);
            return;
        }

        // 아직 M개를 다 선택하지 못했다면, 다음 치킨집을 선택
        for (int i = start; i < chickens.size(); i++) {
            selectedChickens[count] = chickens.get(i);
            // 재귀를 통해서 트리처럼 뻗어나감
            find(i + 1, count + 1);
        }
    }

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}