
import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 驗證是否為有效 BST
    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    // 2. 找出不符規則的節點（中序走訪非遞增）
    public static List<Integer> findInvalidNodes(TreeNode root) {
        List<Integer> invalid = new ArrayList<>();
        TreeNode[] prev = new TreeNode[1];
        inOrderCheck(root, prev, invalid);
        return invalid;
    }

    private static void inOrderCheck(TreeNode node, TreeNode[] prev, List<Integer> invalid) {
        if (node == null) {
            return;
        }
        inOrderCheck(node.left, prev, invalid);
        if (prev[0] != null && node.val <= prev[0].val) {
            invalid.add(node.val);
        }
        prev[0] = node;
        inOrderCheck(node.right, prev, invalid);
    }

    // 3. 修復錯誤 BST（兩個節點 swap）
    public static void recoverTree(TreeNode root) {
        TreeNode[] nodes = new TreeNode[3]; // [first, second, prev]
        recoverHelper(root, nodes);
        if (nodes[0] != null && nodes[1] != null) {
            int tmp = nodes[0].val;
            nodes[0].val = nodes[1].val;
            nodes[1].val = tmp;
        }
    }

    private static void recoverHelper(TreeNode node, TreeNode[] nodes) {
        if (node == null) {
            return;
        }

        recoverHelper(node.left, nodes);

        if (nodes[2] != null && node.val < nodes[2].val) {
            if (nodes[0] == null) {
                nodes[0] = nodes[2];
            }
            nodes[1] = node;
        }

        nodes[2] = node;
        recoverHelper(node.right, nodes);
    }

    // 4. 移除最少節點讓樹變成有效 BST
    public static int minRemovalsToBST(TreeNode root) {
        return countInvalid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static int countInvalid(TreeNode node, long min, long max) {
        if (node == null) {
            return 0;
        }
        if (node.val <= min || node.val >= max) {
            // 無法當作 BST 的節點 → 整個子樹都要重算
            return 1 + countInvalid(node.left, min, max) + countInvalid(node.right, min, max);
        }
        return countInvalid(node.left, min, node.val) + countInvalid(node.right, node.val, max);
    }

    public static void inorderPrint(TreeNode root) {
        if (root != null) {
            inorderPrint(root.left);
            System.out.print(root.val + " ");
            inorderPrint(root.right);
        }
    }

    public static void main(String[] args) {
        // 錯誤 BST 範例：
        //      3
        //     / \
        //    1   4
        //       /
        //      2   <-- 這個位置錯誤
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println("是否為有效 BST: " + isValidBST(root));
        System.out.println("不符合 BST 規則的節點: " + findInvalidNodes(root));
        System.out.print("中序走訪（修復前）: ");
        inorderPrint(root);
        System.out.println();

        recoverTree(root);
        System.out.print("中序走訪（修復後）: ");
        inorderPrint(root);
        System.out.println();

        System.out.println("修復後是否為 BST: " + isValidBST(root));
        System.out.println("最少需移除節點數讓其成為 BST: " + minRemovalsToBST(root));
    }
}
