
public class RBDeleteFixupExercise {

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

        private Node root;

        public void insert(int val) {
            Node node = new Node(val);
            root = bstInsert(root, node);
            fixInsert(node);
        }

        public void delete(int val) {
            Node node = search(root, val);
            if (node != null) {
                deleteNode(node);
            }
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

        private void deleteNode(Node z) {
            Node y = z;
            Node x;
            boolean yOriginalColor = y.color;

            if (z.left == null) {
                x = z.right;
                transplant(z, z.right);
            } else if (z.right == null) {
                x = z.left;
                transplant(z, z.left);
            } else {
                y = minimum(z.right);
                yOriginalColor = y.color;
                x = y.right;
                if (y.parent == z) {
                    if (x != null) {
                        x.parent = y;
                    }
                } else {
                    transplant(y, y.right);
                    y.right = z.right;
                    y.right.parent = y;
                }
                transplant(z, y);
                y.left = z.left;
                y.left.parent = y;
                y.color = z.color;
            }

            if (yOriginalColor == BLACK) {
                fixDelete(x, z.parent);  // 傳入 x 的父節點供判斷 null 時參考
            }
        }

        private void fixDelete(Node x, Node parent) {
            while (x != root && (x == null || x.color == BLACK)) {
                if (x == (parent.left)) {
                    Node w = parent.right;
                    // Case 1: 兄弟為紅
                    if (w != null && w.color == RED) {
                        w.color = BLACK;
                        parent.color = RED;
                        leftRotate(parent);
                        w = parent.right;
                    }
                    // Case 2: 兄弟黑，兩個子也黑
                    if ((w.left == null || w.left.color == BLACK)
                            && (w.right == null || w.right.color == BLACK)) {
                        if (w != null) {
                            w.color = RED;
                        }
                        x = parent;
                        parent = x.parent;
                    } else {
                        // Case 3: 兄弟黑，左紅右黑
                        if (w.right == null || w.right.color == BLACK) {
                            if (w.left != null) {
                                w.left.color = BLACK;
                            }
                            w.color = RED;
                            rightRotate(w);
                            w = parent.right;
                        }
                        // Case 4: 兄弟黑，右紅
                        w.color = parent.color;
                        parent.color = BLACK;
                        if (w.right != null) {
                            w.right.color = BLACK;
                        }
                        leftRotate(parent);
                        x = root;
                        break;
                    }
                } else {
                    // 對稱處理
                    Node w = parent.left;
                    if (w != null && w.color == RED) {
                        w.color = BLACK;
                        parent.color = RED;
                        rightRotate(parent);
                        w = parent.left;
                    }
                    if ((w.right == null || w.right.color == BLACK)
                            && (w.left == null || w.left.color == BLACK)) {
                        if (w != null) {
                            w.color = RED;
                        }
                        x = parent;
                        parent = x.parent;
                    } else {
                        if (w.left == null || w.left.color == BLACK) {
                            if (w.right != null) {
                                w.right.color = BLACK;
                            }
                            w.color = RED;
                            leftRotate(w);
                            w = parent.left;
                        }
                        w.color = parent.color;
                        parent.color = BLACK;
                        if (w.left != null) {
                            w.left.color = BLACK;
                        }
                        rightRotate(parent);
                        x = root;
                        break;
                    }
                }
            }

            if (x != null) {
                x.color = BLACK;
            }
        }

        private void transplant(Node u, Node v) {
            if (u.parent == null) {
                root = v; 
            }else if (u == u.parent.left) {
                u.parent.left = v; 
            }else {
                u.parent.right = v;
            }

            if (v != null) {
                v.parent = u.parent;
            }
        }

        private Node minimum(Node node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        private Node search(Node node, int val) {
            while (node != null && node.val != val) {
                node = val < node.val ? node.left : node.right;
            }
            return node;
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

        public void printInOrder() {
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
        }

        System.out.print("插入完畢：");
        tree.printInOrder();

        int[] deleteVals = {1, 5, 20};
        for (int v : deleteVals) {
            System.out.println("刪除 " + v + " 後：");
            tree.delete(v);
            tree.printInOrder();
        }
    }
}
