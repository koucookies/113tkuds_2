
import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {

        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // 1. 總和與平均值
    public static int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.data + sum(root.left) + sum(root.right);
    }

    public static int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }

    public static double average(TreeNode root) {
        int totalSum = sum(root);
        int totalCount = count(root);
        return totalCount == 0 ? 0 : (double) totalSum / totalCount;
    }

    // 2. 最大值與最小值
    public static int findMax(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        return Math.max(root.data, Math.max(findMax(root.left), findMax(root.right)));
    }

    public static int findMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        return Math.min(root.data, Math.min(findMin(root.left), findMin(root.right)));
    }

    // 3. 計算寬度（每層節點數最大值）
    public static int getMaxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return maxWidth;
    }

    // 4. 是否為完全二元樹
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                end = true;
            } else {
                if (end) {
                    return false; // 若前面出現過 null，後面又有節點 => 非完全二元樹

                                }queue.offer(current.left);
                queue.offer(current.right);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // 測試樹結構
        //       10
        //      /  \
        //     5    15
        //    / \     \
        //   3   7     18

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        System.out.println("總和：" + sum(root));
        System.out.println("平均值：" + average(root));
        System.out.println("最大值：" + findMax(root));
        System.out.println("最小值：" + findMin(root));
        System.out.println("最大寬度：" + getMaxWidth(root));
        System.out.println("是否為完全二元樹：" + isCompleteBinaryTree(root));
    }
}
