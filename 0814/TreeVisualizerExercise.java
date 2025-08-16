
import java.util.*;

public class TreeVisualizerExercise {

    // ====== Node 類別（AVL）======
    static class Node {

        int key;
        int height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    // ====== AVL Tree 實作 ======
    static class AVLTree {

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

            updateHeight(node);
            return balance(node);
        }

        private void updateHeight(Node node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }

        private int height(Node node) {
            return node == null ? 0 : node.height;
        }

        private int getBalance(Node node) {
            return height(node.left) - height(node.right);
        }

        private Node rotateRight(Node y) {
            Node x = y.left;
            Node T2 = x.right;
            x.right = y;
            y.left = T2;
            updateHeight(y);
            updateHeight(x);
            return x;
        }

        private Node rotateLeft(Node x) {
            Node y = x.right;
            Node T2 = y.left;
            y.left = x;
            x.right = T2;
            updateHeight(x);
            updateHeight(y);
            return y;
        }

        private Node balance(Node node) {
            int balance = getBalance(node);
            if (balance > 1 && getBalance(node.left) >= 0) {
                return rotateRight(node);
            }
            if (balance > 1 && getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if (balance < -1 && getBalance(node.right) <= 0) {
                return rotateLeft(node);
            }
            if (balance < -1 && getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
            return node;
        }
    }

    // ====== 視覺化函數 ======
    static void printTree(Node root) {
        printTree(root, "", true);
    }

    static void printTree(Node node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }

        System.out.println(prefix + (isTail ? "└── " : "├── ")
                + node.key + "(BF=" + (getBalance(node)) + ")");

        List<Node> children = new ArrayList<>();
        if (node.left != null) {
            children.add(node.left);
        }
        if (node.right != null) {
            children.add(node.right);
        }

        for (int i = 0; i < children.size(); i++) {
            printTree(children.get(i),
                    prefix + (isTail ? "    " : "│   "),
                    i == children.size() - 1);
        }
    }

    private static int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private static int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // ====== 主程式 ======
    public static void main(String[] args) throws InterruptedException {
        AVLTree tree = new AVLTree();
        int[] values = {10, 20, 5, 6, 15, 30, 25};

        for (int val : values) {
            tree.insert(val);
            System.out.println("插入節點：" + val);
            printTree(tree.root);
            System.out.println("-------------\n");
            Thread.sleep(500); // 動畫延遲
        }
    }
}
