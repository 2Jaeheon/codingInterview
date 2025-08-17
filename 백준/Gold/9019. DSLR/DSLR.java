import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // D S L R을 사용하는 계산기
        // 레지스터에는 0 ~ 10000의 십진수 저장
        // 각 명령어는 레지스터에 저장된 n을 변환
        int T = sc.nextInt(); // 테스트 케이스 개수
    
        // BFS 아닐까??
        // 각 상황에서 DSLR 네 가지를 모두 수행해볼 수 있도록 하면 어떨까

        // Queue에는 뭘 저장? => Integer
        // 방문처리 필요? => X
        // D,S,L,R 연산 필요
        char[] operators = new char[]{'D', 'S', 'L', 'R'};

        for(int i = 0; i < T; i++) {
            int A = sc.nextInt(); // 레지스터의 초기값
            int B = sc.nextInt(); // 레지스터의 최종값

            Queue<Node> queue = new ArrayDeque<>();
            queue.offer(new Node(A));
            boolean[] visited = new boolean[10000];
            visited[A] = true;

            // 큐가 빌때까지 반복
            while(!queue.isEmpty()) {
                Node cur = queue.poll();
                int curN = cur.number;

                if(curN == B) {
                    for(char C : cur.list) {
                        System.out.print(C);
                    }
                    System.out.println();
                }
                
                for(char op : operators) {
                    int next = 0;
                    
                    if(op == 'D') { // D operation
                        next = (2 * curN) % 10000;
                    } else if(op == 'S') { // S operation
                        next = (curN + 9999) % 10000;
                    } else if(op == 'L') { // L operation
                        next = (curN % 1000) * 10 + (curN / 1000);
                    } else if(op == 'R') { // R operation
                        next = (curN % 10) * 1000 + (curN / 10);
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(new Node(next, op, cur.list));
                    }
                }
            }
        }
    }

    public static class Node {
        int number;
        List<Character> list = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }

        // 부모의 리스트를 그대로 더한다음 op를 추가해서 누적
        public Node(int number, char op, List<Character> parentList) {
            this.number = number;
            this.list.addAll(parentList);
            this.list.add(op);
        }
    }
}
