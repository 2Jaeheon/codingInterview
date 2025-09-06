import java.util.*;

public class Main{
    static Map<Character, Node> tree = new HashMap<>();

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 입력: 노드, 왼쪽 자식, 오른쪽 자식
        // 트리 자료구조를 구축한 뒤에 재귀를 기반으로 한 순회를 해주면 됨
        
        for (int i = 0; i < n; i++) {
            char parent = sc.next().charAt(0);
            char left = sc.next().charAt(0);
            char right = sc.next().charAt(0);

            // 부모 노드가 없으면 생성
            tree.putIfAbsent(parent, new Node(parent));
            Node parentNode = tree.get(parent);

            if (left != '.') {
                tree.putIfAbsent(left, new Node(left));
                parentNode.left = tree.get(left);
            }
            if (right != '.') {
                tree.putIfAbsent(right, new Node(right));
                parentNode.right = tree.get(right);
            }
        }

        
        Node root = tree.get('A'); // 항상 A가 루트
        // 전위
        preorder(root);
        System.out.println();
        // 중위
        inorder(root);
        System.out.println();
        // 후위
        postorder(root);
        System.out.println();
    }

    static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value);
        inorder(node.right);
    }

    static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value);
    }

    static class Node {
        char value;
        Node left;
        Node right;

        Node(char value) {
            this.value = value;
        }
    }
}
