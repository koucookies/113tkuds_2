
import java.util.*;

public class TreePathProblems {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 所有根到葉路徑
    public static List<List<Integer>> allPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        path.add(node.val);

        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            backtrack(node.left, path, result);
            backtrack(node.right, path, result);
        }

        path.remove(path.size() - 1);
    }

    // 2. 是否有目標總和
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 最大總和路徑
    public static List<Integer> maxSumPath(TreeNode root) {
        List<Integer> bestPath = new ArrayList<>();
        maxSumHelper(root, new ArrayList<>(), bestPath, new int[]{Integer.MIN_VALUE}, 0);
        return bestPath;
    }

    private static void maxSumHelper(TreeNode node, List<Integer> path, List<Integer> bestPath, int[] maxSum, int currentSum) {
        if (node == null) {
            return;
        }

        path.add(node.val);
        currentSum += node.val;

        if (node.left == null && node.right == null) {
            if (currentSum > maxSum[0]) {
                maxSum[0] = currentSum;
                bestPath.clear();
                bestPath.addAll(new ArrayList<>(path));
            }
        }

        maxSumHelper(node.left, path, bestPath, maxSum, currentSum);
        maxSumHelper(node.right, path, bestPath, maxSum, currentSum);
        path.remove(path.size() - 1);
    }

    // 4. 最大路徑和（任意兩節點）
    static int maxPathSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        dfsMaxPath(root);
        return maxPathSum;
    }

    private static int dfsMaxPath(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(0, dfsMaxPath(node.left));
        int right = Math.max(0, dfsMaxPath(node.right));

        maxPathSum = Math.max(maxPathSum, left + right + node.val);
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        // 樣例樹：
        //      10
        //     /  \
        //    5    -3
        //   / \     \
        //  3   2     11
        // / \   \
        //3  -2   1

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        System.out.println("1. 所有根到葉節點的路徑：");
        for (List<Integer> path : allPaths(root)) {
            System.out.println(path);
        }

        System.out.println("2. 是否有總和為 18 的路徑: " + hasPathSum(root, 18));

        System.out.println("3. 最大總和根到葉路徑: " + maxSumPath(root));

        System.out.println("4. 任意兩節點間的最大總和（樹的直徑）: " + maxPathSum(root));
    }
}
