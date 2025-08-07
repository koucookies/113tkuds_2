
import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 每層存在不同 List 中
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(level);
        }

        return result;
    }

    // 2. 之字形層序
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    level.addLast(node.val);
                } else {
                    level.addFirst(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(level);
            leftToRight = !leftToRight;
        }

        return result;
    }

    // 3. 每層最後一個節點
    public static List<Integer> rightMostEachLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode last = null;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                last = node;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(last.val);
        }

        return result;
    }

    // 4. 垂直層序（根據 x 軸位置分組）
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();

        nodeQueue.offer(root);
        colQueue.offer(0);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int col = colQueue.poll();

            map.computeIfAbsent(col, k -> new ArrayList<>()).add(node.val);

            if (node.left != null) {
                nodeQueue.offer(node.left);
                colQueue.offer(col - 1);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                colQueue.offer(col + 1);
            }
        }

        result.addAll(map.values());
        return result;
    }

    public static void main(String[] args) {
        // 範例樹：
        //      1
        //     / \
        //    2   3
        //   / \   \
        //  4   5   6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println("1. 一層一層儲存：");
        for (List<Integer> level : levelOrder(root)) {
            System.out.println(level);
        }

        System.out.println("2. 之字形走訪：");
        for (List<Integer> level : zigzagLevelOrder(root)) {
            System.out.println(level);
        }

        System.out.println("3. 每層最後一個節點：");
        System.out.println(rightMostEachLevel(root));

        System.out.println("4. 垂直層序走訪：");
        for (List<Integer> group : verticalOrder(root)) {
            System.out.println(group);
        }
    }
}
