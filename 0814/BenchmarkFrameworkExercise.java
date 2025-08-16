
import java.util.*;

public class BenchmarkFrameworkExercise {

    // ===== 接口定義 =====
    interface Tree {

        void insert(int key);

        boolean search(int key);

        void delete(int key);
    }

    // ===== 模擬 AVL Tree 實作 =====
    static class DummyAVLTree implements Tree {

        Set<Integer> tree = new TreeSet<>(); // 模擬用

        public void insert(int key) {
            tree.add(key);
        }

        public boolean search(int key) {
            return tree.contains(key);
        }

        public void delete(int key) {
            tree.remove(key);
        }
    }

    // ===== 模擬 Red-Black Tree 實作 =====
    static class DummyRBTree implements Tree {

        Set<Integer> tree = new TreeSet<>(); // 模擬用

        public void insert(int key) {
            tree.add(key);
        }

        public boolean search(int key) {
            return tree.contains(key);
        }

        public void delete(int key) {
            tree.remove(key);
        }
    }

    // ===== 測試器實作 =====
    static class TreeBenchmark {

        private final Tree tree;
        private final String name;
        private final Random rand = new Random();

        public TreeBenchmark(Tree tree, String name) {
            this.tree = tree;
            this.name = name;
        }

        public void runTest(int size) {
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = rand.nextInt(size * 10);
            }

            System.out.println("===== 測試：" + name + "，資料量：" + size + " =====");

            // 插入測試
            long startInsert = System.nanoTime();
            for (int key : data) {
                tree.insert(key);
            }
            long endInsert = System.nanoTime();
            System.out.println("插入時間: " + (endInsert - startInsert) / 1e6 + " ms");

            // 查詢測試
            long startSearch = System.nanoTime();
            for (int i = 0; i < size / 2; i++) {
                tree.search(data[rand.nextInt(size)]);
            }
            long endSearch = System.nanoTime();
            System.out.println("查詢時間: " + (endSearch - startSearch) / 1e6 + " ms");

            // 刪除測試
            long startDelete = System.nanoTime();
            for (int i = 0; i < size / 2; i++) {
                tree.delete(data[rand.nextInt(size)]);
            }
            long endDelete = System.nanoTime();
            System.out.println("刪除時間: " + (endDelete - startDelete) / 1e6 + " ms");
            System.out.println();
        }
    }

    // ===== 主程式 =====
    public static void main(String[] args) {
        int dataSize = 100_000;

        TreeBenchmark avlTest = new TreeBenchmark(new DummyAVLTree(), "AVL Tree");
        TreeBenchmark rbTest = new TreeBenchmark(new DummyRBTree(), "Red-Black Tree");

        avlTest.runTest(dataSize);
        rbTest.runTest(dataSize);
    }
}
