package graph;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class 개념 {

    public static void main(String[] args) {
        Graph graph = new Graph(9);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 8);

        Graph graph2 = new Graph(9);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(2, 4);
        graph2.addEdge(2, 3);
        graph2.addEdge(3, 4);
        graph2.addEdge(3, 5);
        graph2.addEdge(5, 6);
        graph2.addEdge(5, 7);
        graph2.addEdge(6, 8);

        graph.dfs();
        System.out.println("\n------------------------------------------");
        graph2.bfs();
    }

}

class Graph {

    class Node {

        int data;
        LinkedList<Node> adjacent;
        boolean isMarked;

        public Node(int data) {
            this.data = data;
            this.isMarked = false;
            adjacent = new LinkedList<Node>();
        }
    }

    Node[] nodes;

    Graph(int size) {
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i);
        }
    }

    void addEdge(int v1, int v2) {
        Node n1 = nodes[v1];
        Node n2 = nodes[v2];

        if (!n1.adjacent.contains(n2)) {
            n1.adjacent.add(n2);
        }
        if (!n2.adjacent.contains(n1)) {
            n2.adjacent.add(n1);
        }
    }

    void dfs() {
        dfs(0);
    }

    void dfs(int index) {
        Node root = nodes[index];
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        root.isMarked = true;

        while (!stack.isEmpty()) {
            // DFS의 동작 과정
            // stack에서 꺼내서 방문하지 않은 인접한 노드들을 stack에 삽입
            // 위 과정을 스택이 빌 때까지 반복
            Node r = stack.pop();

            for (Node n : r.adjacent) {
                if (!n.isMarked) {
                    n.isMarked = true;
                    stack.push(n);
                }
            }
            visit(r);
        }
    }

    void visit(Node n) {
        System.out.print(n.data + "   ");
    }

    void bfs() {
        bfs(0);
    }

    void bfs(int index) {
        Node root = nodes[index];
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.addLast(root);
        root.isMarked = true;

        // BFS 원리
        // queue에서 데이터를 하나 꺼내서 방문한 노드인지 확인 후 방문하지 않은 노드들을 queue에 추가
        // 위 과정을 queue가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Node r = queue.pollFirst();

            for (Node n : r.adjacent) {
                if (!n.isMarked) {
                    n.isMarked = true;
                    queue.addLast(n);
                }
            }

            visit(r);
        }
    }

    void dfsR(Node r) {
        if (r == null) {
            return;
        }
        r.isMarked = true;
        visit(r);

        for (Node n : r.adjacent) {
            if (!n.isMarked) {
                dfsR(n);
            }
        }
    }

    void dfsR() {
        dfsR(0);
    }

    void dfsR(int index) {
        Node r = nodes[index];
        dfsR(r);
    }
}