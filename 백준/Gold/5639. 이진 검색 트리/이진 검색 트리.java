import java.util.*;

public class Main{
    static int idx = 0;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        // 전위순회값을 트리로 구축한 뒤
        // 트리에서 후위순회를 하면 됨.

        List<Integer> pre = new ArrayList<>();
        while (sc.hasNextInt()) {
            pre.add(sc.nextInt());
        }

        int n = pre.size();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = pre.get(i);
        }

        // 전위순회로부터 BST를 생성
        idx = 0;
        Node root = build(arr, Long.MIN_VALUE, Long.MAX_VALUE);

        // 후위순회
        postorder(root);
    }

    // 전위 배열을 한 번만 훑으며 [low, high] 범위를 이용해 BST 복원
    static Node build(int[] pre, long low, long high) {
        // 인덱스가 초과하는 경우
        if(idx >= pre.length) {
            return null;
        }

        // v: 전위순회해서 나온 값
        int v = pre[idx];

        // 범위를 벗어나면 안 됨
        if(v <= low || v >= high) {
            return null;
        }

        idx++;

        // root 노드가 정상적으로 만들어지고, 좌측과 우측이 만들어져야함.
        Node root = new Node(v);
        root.left  = build(pre, low, v);   // 왼쪽은 모두 v보다 작아야 함
        root.right = build(pre, v, high);  // 오른쪽은 모두 v보다 커야 함
        return root;
    }

    static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.println(node.value);
    }
    
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
