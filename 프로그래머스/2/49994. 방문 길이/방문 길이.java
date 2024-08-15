import java.util.*;
class Solution {
    // Node class to represent a path segment
    static class Node {
        int x1, y1, x2, y2;

        Node(int x1, int y1, int x2, int y2) {
            this.x1 = Math.min(x1, x2);
            this.y1 = Math.min(y1, y2);
            this.x2 = Math.max(x1, x2);
            this.y2 = Math.max(y1, y2);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return x1 == node.x1 && y1 == node.y1 && x2 == node.x2 && y2 == node.y2;
        }

        @Override
        public int hashCode() {
            int result = Integer.hashCode(x1);
            result = 31 * result + Integer.hashCode(y1);
            result = 31 * result + Integer.hashCode(x2);
            result = 31 * result + Integer.hashCode(y2);
            return result;
        }
    }

    public int solution(String dirs) {
        Set<Node> visited = new HashSet<>();
        int x = 5, y = 5; // Start position
        int count = 0;

        for (char dir : dirs.toCharArray()) {
            int nx = x, ny = y;

            switch (dir) {
                case 'U': ny++; break;
                case 'D': ny--; break;
                case 'R': nx++; break;
                case 'L': nx--; break;
            }

            // Check if out of bounds
            if (nx < 0 || nx > 10 || ny < 0 || ny > 10) continue;

            // Create nodes representing the path
            Node currentPath = new Node(x, y, nx, ny);

            // Check if the path has been visited
            if (!visited.contains(currentPath)) {
                visited.add(currentPath);
                count++;
            }

            // Update position
            x = nx;
            y = ny;
        }

        return count;
    }
}
