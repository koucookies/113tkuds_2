
import java.util.*;

public class RBTreeIteratorExercise {

    static final boolean RED = true;
    static final boolean BLACK = false;

    static class Node {

        int val;
        boolean color;
        Node left, right, parent;

        Node(int val) {
            this.val = val;
            this.color = RED;
        }
    }

    static class RedBlackTree {

        Node root;

        public void insert(int val) {
            Node node = new Node(val);
            root = bstInsert(root, node);
            fixInsert(node);
        }

        private Node bstInsert(Node root, Node node) {
            if (root == null) {
                return node;
            }

            if (node.val < root.val) {
                root.left = bstInsert(root.left, node);
                root.left.parent = root;
            } else {
                root.right = bstInsert(root.right, node);
                root.right.parent = root;
            }
            return root;
        }

        private void fixInsert(Node node) {
            while (node != root && node.parent.color == RED) {
                Node parent = node.parent;
                Node grandparent = parent.parent;

                if (parent == grandparent.left) {
                    Node uncle = grandparent.right;
                    if (uncle != null && uncle.color == RED) {
                        parent.color = BLACK;
                        uncle.color = BLACK;
                        grandparent.color = RED;
                        node = grandparent;
                    } else {
                        if (node == parent.right) {
                            node = parent;
                            leftRotate(node);
                        }
                        parent.color = BLACK;
                        grandparent.color = RED;
                        rightRotate(grandparent);
                    }
                } else {
                    Node uncle = grandparent.left;
                    if (uncle != null && uncle.color == RED) {
                        parent.color = BLACK;
                        uncle.color = BLACK;
                        grandparent.color = RED;
                        node = grandparent;
                    } else {
                        if (node == parent.left) {
                            node = parent;
                            rightRotate(node);
                        }
                        parent.color = BLACK;
                        grandparent.color = RED;
                        leftRotate(grandparent);
                    }
                }
            }
            root.color = BLACK;
        }

        private void leftRotate(Node x) {
            Node y = x.right;
            x.right = y.left;
            if (y.left != null) {
                y.left.parent = x;
            }

            y.parent = x.parent;
            if (x.parent == null) {
                root = y; 
            }else if (x == x.parent.left) {
                x.parent.left = y; 
            }else {
                x.parent.right = y;
            }

            y.left = x;
            x.parent = y;
        }

        private void rightRotate(Node x) {
            Node y = x.left;
            x.left = y.right;
            if (y.right != null) {
                y.right.parent = x;
            }

            y.parent = x.parent;
            if (x.parent == null) {
                root = y; 
            }else if (x == x.parent.right) {
                x.parent.right = y; 
            }else {
                x.parent.left = y;
            }

            y.right = x;
            x.parent = y;
        }

        public TreeIterator iterator() {
            return new TreeIterator(root, null, null, true);
        }

        public TreeIterator reverseIterator() {
            return new TreeIterator(root, null, null, false);
        }

        public TreeIterator rangeIterator(int low, int high) {
            return new TreeIterator(root, low, high, true);
        }
    }

    static class TreeIterator {

        Stack<Node> stack = new Stack<>();
        boolean ascending;
        Integer low, high;

        public TreeIterator(Node root, Integer low, Integer high, boolean ascending) {
            this.ascending = ascending;
            this.low = low;
            this.high = high;
            pushNodes(root);
        }

        private void pushNodes(Node node) {
            while (node != null) {
                if (ascending) {
                    if (low == null || node.val >= low) {
                        stack.push(node);
                        node = node.left;
                    } else {
                        node = node.right;
                    }
                } else {
                    if (high == null || node.val <= high) {
                        stack.push(node);
                        node = node.right;
                    } else {
                        node = node.left;
                    }
                }
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            Node node = stack.pop();
            int val = node.val;

            if (ascending) {
                if (node.right != null) {
                    pushNodes(node.right);
                }
            } else {
                if (node.left != null) {
                    pushNodes(node.left);
                }
            }

            // 範圍過濾
            if ((low != null && val < low) || (high != null && val > high)) {
                return hasNext() ? next() : -1;
            }

            return val;
        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        for (int v : values) {
            tree.insert(v);
        }

        System.out.print("中序遍歷: ");
        TreeIterator it = tree.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        System.out.print("反向遍歷: ");
        TreeIterator rit = tree.reverseIterator();
        while (rit.hasNext()) {
            System.out.print(rit.next() + " ");
        }
        System.out.println();

        System.out.print("範圍遍歷[10~30]: ");
        TreeIterator rangeIt = tree.rangeIterator(10, 30);
        while (rangeIt.hasNext()) {
            System.out.print(rangeIt.next() + " ");
        }
        System.out.println();
    }
}
