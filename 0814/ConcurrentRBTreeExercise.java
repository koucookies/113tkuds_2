
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentRBTreeExercise {

    static final boolean RED = true;
    static final boolean BLACK = false;

    static class Node {

        int key;
        boolean color;
        Node left, right, parent;

        Node(int key) {
            this.key = key;
            this.color = RED;
        }
    }

    static class ConcurrentRedBlackTree {

        private Node root;
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        // 公開 insert 方法
        public void insert(int key) {
            lock.writeLock().lock();
            try {
                root = insertNode(root, key);
                root.color = BLACK;
            } finally {
                lock.writeLock().unlock();
            }
        }

        // 公開 search 方法
        public boolean search(int key) {
            lock.readLock().lock();
            try {
                Node node = root;
                while (node != null) {
                    if (key < node.key) {
                        node = node.left; 
                    }else if (key > node.key) {
                        node = node.right; 
                    }else {
                        return true;
                    }
                }
                return false;
            } finally {
                lock.readLock().unlock();
            }
        }

        // 插入 + 自動修復
        private Node insertNode(Node root, int key) {
            if (root == null) {
                return new Node(key);
            }

            if (key < root.key) {
                root.left = insertNode(root.left, key);
                root.left.parent = root;
            } else if (key > root.key) {
                root.right = insertNode(root.right, key);
                root.right.parent = root;
            }

            root = fixUp(root);
            return root;
        }

        // 紅黑樹修復邏輯（左旋、右旋、顏色翻轉）
        private Node fixUp(Node h) {
            if (isRed(h.right) && !isRed(h.left)) {
                h = rotateLeft(h);
            }
            if (isRed(h.left) && isRed(h.left.left)) {
                h = rotateRight(h);
            }
            if (isRed(h.left) && isRed(h.right)) {
                flipColors(h);
            }
            return h;
        }

        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = h.color;
            h.color = RED;
            return x;
        }

        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = RED;
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

        // 印出中序（讀鎖保護）
        public void inorderPrint() {
            lock.readLock().lock();
            try {
                inorderPrint(root);
                System.out.println();
            } finally {
                lock.readLock().unlock();
            }
        }

        private void inorderPrint(Node node) {
            if (node != null) {
                inorderPrint(node.left);
                System.out.print(node.key + " ");
                inorderPrint(node.right);
            }
        }
    }

    public static void main(String[] args) {
        ConcurrentRedBlackTree tree = new ConcurrentRedBlackTree();

        // 插入執行緒
        Thread writer = new Thread(() -> {
            int[] keys = {10, 20, 5, 6, 1, 15};
            for (int key : keys) {
                tree.insert(key);
                System.out.println("Inserted: " + key);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        });

        // 搜尋執行緒
        Thread reader = new Thread(() -> {
            int[] test = {15, 99, 1, 6};
            for (int k : test) {
                boolean found = tree.search(k);
                System.out.println("Search " + k + ": " + found);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                }
            }
        });

        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
        }

        System.out.print("Final Tree Inorder: ");
        tree.inorderPrint();
    }
}
