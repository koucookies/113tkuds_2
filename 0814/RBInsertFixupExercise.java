
public class RBInsertFixupExercise {

    static final boolean RED = true;
    static final boolean BLACK = false;

    static class Node {

        int val;
        boolean color;
        Node left, right, parent;

        Node(int val) {
            this.val = val;
            this.color = RED; // 新插入節點預設為紅色
        }
    }

    static class RedBlackTree {

        Node root;

        void insert(int val) {
            Node newNode = new Node(val);
            root = bstInsert(root, newNode);
            fixInsert(newNode);
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

                    // Case 1: 叔叔是紅色
                    if (uncle != null && uncle.color == RED) {
                        parent.color = BLACK;
                        uncle.color = BLACK;
                        grandparent.color = RED;
                        node = grandparent;
                    } else {
                        // Case 2: 叔叔是黑色且 node 是右子節點
                        if (node == parent.right) {
                            node = parent;
                            leftRotate(node);
                        }
                        // Case 3: node 是左子節點
                        parent.color = BLACK;
                        grandparent.color = RED;
                        rightRotate(grandparent);
                    }
                } else {
                    Node uncle = grandparent.left;

                    // 對稱 Case 1
                    if (uncle != null && uncle.color == RED) {
                        parent.color = BLACK;
                        uncle.color = BLACK;
                        grandparent.color = RED;
                        node = grandparent;
                    } else {
                        // 對稱 Case 2
                        if (node == parent.left) {
                            node = parent;
                            rightRotate(node);
                        }
                        // 對稱 Case 3
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

        // 中序列印 + 顏色
        void printInOrder() {
            printHelper(root);
            System.out.println();
        }

        private void printHelper(Node node) {
            if (node != null) {
                printHelper(node.left);
                System.out.print(node.val + "(" + (node.color == RED ? "R" : "B") + ") ");
                printHelper(node.right);
            }
        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        int[] values = {10, 20, 30, 15, 25, 5, 1};

        for (int v : values) {
            tree.insert(v);
            System.out.print("插入 " + v + " 後：");
            tree.printInOrder();
        }
    }
}
