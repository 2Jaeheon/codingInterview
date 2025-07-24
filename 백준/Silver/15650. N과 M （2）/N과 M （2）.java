import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 자연수 N, M이 주어질 때 다음을 만족하는 길이가 M인 모든 수열
        // 1부터 N까지 자연수 중에서 중복없이 M개를 고른 수열
        // 고른 수열은 오름차순

        int n = sc.nextInt();
        int m = sc.nextInt();
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(0, 1, new ArrayList<>()));
        
        while(!stack.isEmpty()) {
            Node current = stack.pop();

            // 현재 스택에서 뺀 노드의 깊이가 m이면 출력
            if (current.depth == m) {
                // 리스트에 담긴 값을 모두 출력
                for (int num : current.list) {
                    System.out.print(num + " ");
                }
                System.out.println();
                continue;
            }

            // 현재 노드의 깊이가 m보다 작은경우
            for(int i = n; i >= current.start; i--) {
                // 스택에 내림차순으로 저장해서 사전순서대로 가능하도록 함.
                // pop과 push 는 반대이니까
                List<Integer> nextList = new ArrayList<>(current.list);

                nextList.add(i);

                stack.push(new Node(current.depth + 1, i + 1, nextList));
            }
        }
        
    }

    static class Node {
        private int depth; // 지금까지 탐험한 깊이
        private int start; // 시작 위치
        List<Integer> list; // 지금까지 고른 수열

        Node(int depth, int start, List<Integer> list) {
            this.depth = depth;
            this.start = start;
            this.list = list;
        }
    }
}