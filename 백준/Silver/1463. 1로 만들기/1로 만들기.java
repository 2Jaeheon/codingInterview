import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 10 -> 9 -> 3 -> 1
        // 3 -> 1
        // 7 -> 6 -> 3 -> 1
        // 5 -> 4 -> 2 -> 1 or 5 -> 4 -> 3 -> 1

        Node node = new Node();
        boolean[] visited = new boolean[n + 1];

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(n, 0));
        visited[n] = true;

        while(!q.isEmpty()) {
            Node current = q.poll();

            if(current.value == 1) {
                System.out.println(current.steps);
                return ;
            }

            int next;

            if(current.value % 3 == 0) {
                next = current.value / 3;
                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(new Node(next, current.steps + 1));
                }
            }

            if(current.value % 2 == 0) {
                next = current.value / 2;
                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(new Node(next, current.steps + 1));
                }
            }
            
            next = current.value - 1;
            if(!visited[next]) {
                visited[next] = true;
                q.offer(new Node(next, current.steps + 1));
            }
        }
    }

    static class Node {
        int value;
        int steps;

        Node(int value, int steps) {
            this.value = value;
            this.steps = steps;
        }

        Node() {
            
        }
    }
}