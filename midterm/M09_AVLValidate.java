
import java.util.*;

public class M09_AVLValidate {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        TreeNode root = buildTree(arr);

        if (!isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (checkAVL(root) == -1) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    // 建樹（層序）
    private static TreeNode buildTree(int[] values) {
        if (values.length == 0 || values[0] == -1) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (i < values.length) {
            TreeNode node = queue.poll();
            if (i < values.length && values[i] != -1) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < values.length && values[i] != -1) {
                node.right = new TreeNode(values[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }

    // 驗證 BST（中序範圍檢查）
    private static boolean isBST(TreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    // 驗證 AVL（後序回傳高度；-1 表不平衡）
    private static int checkAVL(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = checkAVL(node.left);
        int right = checkAVL(node.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }
}
/*
 * Time Complexity: O(n)
 * 說明：
 * 1. 建樹過程為 O(n)，每個節點處理一次。
 * 2. BST 驗證為 O(n)，遞迴檢查每個節點一次。
 * 3. AVL 驗證也是 O(n)，後序遍歷一次所有節點。
 */
