
import java.util.*;

public class IntervalTreeExercise {

    static class Interval {

        int low, high;

        Interval(int low, int high) {
            this.low = low;
            this.high = high;
        }

        boolean overlaps(Interval other) {
            return this.low <= other.high && other.low <= this.high;
        }

        boolean contains(int point) {
            return point >= low && point <= high;
        }

        public String toString() {
            return "[" + low + "," + high + "]";
        }
    }

    static class Node {

        Interval interval;
        int max;  // 最大 high
        Node left, right;
        boolean color; // true: RED, false: BLACK

        Node(Interval interval) {
            this.interval = interval;
            this.max = interval.high;
            this.color = true;
        }
    }

    static class IntervalTree {

        private Node root;
        private static final boolean RED = true;
        private static final boolean BLACK = false;

        // 左旋
        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = h.color;
            h.color = RED;
            updateMax(h);
            updateMax(x);
            return x;
        }

        // 右旋
        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = RED;
            updateMax(h);
            updateMax(x);
            return x;
        }

        private void flipColors(Node h) {
            h.color = RED;
            if (h.left != null) {
                h.left.color = BLACK;
            }
            if (h.right != null) {
                h.right.color = BLACK;
            }
        }

        private boolean isRed(Node node) {
            return node != null && node.color == RED;
        }

        private void updateMax(Node node) {
            node.max = node.interval.high;
            if (node.left != null) {
                node.max = Math.max(node.max, node.left.max);
            }
            if (node.right != null) {
                node.max = Math.max(node.max, node.right.max);
            }
        }

        public void insert(int low, int high) {
            root = insert(root, new Interval(low, high));
            root.color = BLACK;
        }

        private Node insert(Node h, Interval interval) {
            if (h == null) {
                return new Node(interval);
            }

            if (interval.low < h.interval.low) {
                h.left = insert(h.left, interval); 
            }else {
                h.right = insert(h.right, interval);
            }

            if (isRed(h.right) && !isRed(h.left)) {
                h = rotateLeft(h);
            }
            if (isRed(h.left) && isRed(h.left.left)) {
                h = rotateRight(h);
            }
            if (isRed(h.left) && isRed(h.right)) {
                flipColors(h);
            }

            updateMax(h);
            return h;
        }

        public void delete(int low, int high) {
            Interval target = new Interval(low, high);
            root = delete(root, target);
            if (root != null) {
                root.color = BLACK;
            }
        }

        private Node delete(Node h, Interval interval) {
            if (h == null) {
                return null;
            }

            if (interval.low < h.interval.low) {
                h.left = delete(h.left, interval);
            } else if (interval.low > h.interval.low || interval.high != h.interval.high) {
                h.right = delete(h.right, interval);
            } else {
                // 刪除節點
                if (h.right == null) {
                    return h.left;
                }
                if (h.left == null) {
                    return h.right;
                }

                Node min = getMin(h.right);
                h.interval = min.interval;
                h.right = delete(h.right, min.interval);
            }

            updateMax(h);
            return h;
        }

        private Node getMin(Node h) {
            while (h.left != null) {
                h = h.left;
            }
            return h;
        }

        // 查詢所有與區間重疊的節點
        public List<Interval> searchOverlaps(int low, int high) {
            Interval target = new Interval(low, high);
            List<Interval> result = new ArrayList<>();
            searchOverlaps(root, target, result);
            return result;
        }

        private void searchOverlaps(Node node, Interval target, List<Interval> result) {
            if (node == null) {
                return;
            }

            if (node.interval.overlaps(target)) {
                result.add(node.interval);
            }
            if (node.left != null && node.left.max >= target.low) {
                searchOverlaps(node.left, target, result);
            }
            searchOverlaps(node.right, target, result);
        }

        // 查詢包含指定點的區間
        public List<Interval> searchContainingPoint(int point) {
            List<Interval> result = new ArrayList<>();
            searchContainingPoint(root, point, result);
            return result;
        }

        private void searchContainingPoint(Node node, int point, List<Interval> result) {
            if (node == null) {
                return;
            }
            if (node.interval.contains(point)) {
                result.add(node.interval);
            }
            if (node.left != null && node.left.max >= point) {
                searchContainingPoint(node.left, point, result);
            }
            searchContainingPoint(node.right, point, result);
        }
    }

    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();

        tree.insert(15, 20);
        tree.insert(10, 30);
        tree.insert(17, 19);
        tree.insert(5, 20);
        tree.insert(12, 15);
        tree.insert(30, 40);

        System.out.println("重疊 [14,16] 的區間: " + tree.searchOverlaps(14, 16));
        System.out.println("包含點 14 的區間: " + tree.searchContainingPoint(14));

        tree.delete(10, 30);
        System.out.println("刪除 [10,30] 後包含點 14 的區間: " + tree.searchContainingPoint(14));
    }
}
