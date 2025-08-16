
import java.util.*;

public class HybridDataStructureExercise {

    interface Tree {

        void insert(int key);

        boolean search(int key);

        List<Integer> inOrder();
    }

    // === AVL Tree ===
    static class AVLTree implements Tree {

        class Node {

            int key, height;
            Node left, right;

            Node(int key) {
                this.key = key;
                height = 1;
            }
        }

        Node root;

        public void insert(int key) {
            root = insert(root, key);
        }

        private Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }
            if (key < node.key) {
                node.left = insert(node.left, key); 
            }else if (key > node.key) {
                node.right = insert(node.right, key); 
            }else {
                return node;
            }

            node.height = 1 + Math.max(height(node.left), height(node.right));
            int balance = getBalance(node);

            if (balance > 1 && key < node.left.key) {
                return rotateRight(node);
            }
            if (balance < -1 && key > node.right.key) {
                return rotateLeft(node);
            }
            if (balance > 1 && key > node.left.key) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if (balance < -1 && key < node.right.key) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            return node;
        }

        private Node rotateLeft(Node y) {
            Node x = y.right;
            Node T2 = x.left;
            x.left = y;
            y.right = T2;
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            return x;
        }

        private Node rotateRight(Node y) {
            Node x = y.left;
            Node T2 = x.right;
            x.right = y;
            y.left = T2;
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            return x;
        }

        private int height(Node node) {
            return (node == null) ? 0 : node.height;
        }

        private int getBalance(Node node) {
            return (node == null) ? 0 : height(node.left) - height(node.right);
        }

        public boolean search(int key) {
            Node node = root;
            while (node != null) {
                if (key == node.key) {
                    return true;
                }
                node = (key < node.key) ? node.left : node.right;
            }
            return false;
        }

        public List<Integer> inOrder() {
            List<Integer> result = new ArrayList<>();
            inOrder(root, result);
            return result;
        }

        private void inOrder(Node node, List<Integer> list) {
            if (node != null) {
                inOrder(node.left, list);
                list.add(node.key);
                inOrder(node.right, list);
            }
        }
    }

    // === Red-Black Tree（簡化版）===
    static class RBTree implements Tree {

        private TreeSet<Integer> tree = new TreeSet<>();

        public void insert(int key) {
            tree.add(key);
        }

        public boolean search(int key) {
            return tree.contains(key);
        }

        public List<Integer> inOrder() {
            return new ArrayList<>(tree);
        }
    }

    // === Hybrid Controller ===
    static class HybridStructure {

        private final Tree avl = new AVLTree();
        private final Tree rbt = new RBTree();

        private Tree activeTree = avl;
        private final Deque<String> recentOps = new LinkedList<>();
        private final int THRESHOLD = 10;

        public void insert(int key) {
            updateOp("write");
            activeTree.insert(key);
        }

        public boolean search(int key) {
            updateOp("read");
            return activeTree.search(key);
        }

        public void printState() {
            System.out.println("Active Tree: " + (activeTree instanceof AVLTree ? "AVL" : "RBTree"));
            System.out.println("Content: " + activeTree.inOrder());
        }

        private void updateOp(String type) {
            recentOps.addLast(type);
            if (recentOps.size() > THRESHOLD) {
                recentOps.pollFirst();
            }
            int reads = 0, writes = 0;
            for (String op : recentOps) {
                if (op.equals("read")) {
                    reads++; 
                }else {
                    writes++;
                }
            }

            if (reads > writes && !(activeTree instanceof AVLTree)) {
                syncData(activeTree, avl);
                activeTree = avl;
                System.out.println("[切換到 AVL Tree]");
            } else if (writes > reads && !(activeTree instanceof RBTree)) {
                syncData(activeTree, rbt);
                activeTree = rbt;
                System.out.println("[切換到 RB Tree]");
            }
        }

        private void syncData(Tree from, Tree to) {
            for (int val : from.inOrder()) {
                to.insert(val);
            }
        }
    }

    // === 測試用 main ===
    public static void main(String[] args) {
        HybridStructure hybrid = new HybridStructure();

        // 寫入操作密集
        for (int i = 1; i <= 6; i++) {
            hybrid.insert(i);
        }

        // 讀取操作密集
        for (int i = 1; i <= 6; i++) {
            hybrid.search(i);
        }

        hybrid.insert(100);
        hybrid.search(3);
        hybrid.printState();
    }
}
