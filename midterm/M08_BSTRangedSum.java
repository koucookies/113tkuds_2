
import java.util.*;

public class M08_BSTRangedSum {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 建立 BST（層序建樹）
    public static TreeNode buildTree(int[] values) {
        if (values.length == 0 || values[0] == -1) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (i < values.length) {
            TreeNode curr = queue.poll();

            if (i < values.length && values[i] != -1) {
                curr.left = new TreeNode(values[i]);
                queue.offer(curr.left);
            }
            i++;

            if (i < values.length && values[i] != -1) {
                curr.right = new TreeNode(values[i]);
                queue.offer(curr.right);
            }
            i++;
        }

        return root;
    }

    // 區間總和，使用剪枝技巧
    public static int rangeSum(TreeNode node, int L, int R) {
        if (node == null) {
            return 0;
        }

        if (node.val < L) {
            return rangeSum(node.right, L, R);
        } else if (node.val > R) {
            return rangeSum(node.left, L, R);
        } else {
            return node.val + rangeSum(node.left, L, R) + rangeSum(node.right, L, R);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] tokens = sc.nextLine().split(" ");
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }

        int L = sc.nextInt();
        int R = sc.nextInt();

        TreeNode root = buildTree(arr);
        int sum = rangeSum(root, L, R);

        System.out.println("Sum: " + sum);
    }
}
