
import java.util.*;

public class TreeReconstruction {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 根據前序與中序重建
    public static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) {
            return null;
        }
        TreeNode root = new TreeNode(pre[ps]);
        int inRoot = inMap.get(root.val);
        int leftSize = inRoot - is;

        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, inRoot - 1, inMap);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, inRoot + 1, ie, inMap);
        return root;
    }

    // 2. 根據後序與中序重建
    public static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPostIn(int[] post, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) {
            return null;
        }
        TreeNode root = new TreeNode(post[pe]);
        int inRoot = inMap.get(root.val);
        int leftSize = inRoot - is;

        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, inRoot - 1, inMap);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, inRoot + 1, ie, inMap);
        return root;
    }

    // 3. 根據層序重建完全二元樹
    public static TreeNode buildCompleteTree(int[] levelOrder) {
        if (levelOrder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode node = queue.poll();
            if (i < levelOrder.length) {
                node.left = new TreeNode(levelOrder[i++]);
                queue.offer(node.left);
            }
            if (i < levelOrder.length) {
                node.right = new TreeNode(levelOrder[i++]);
                queue.offer(node.right);
            }
        }
        return root;
    }

    // 4. 中序列印驗證
    public static void inorderPrint(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] levelOrder = {3, 9, 20, 15, 7};

        TreeNode tree1 = buildFromPreIn(preorder, inorder);
        TreeNode tree2 = buildFromPostIn(postorder, inorder);
        TreeNode tree3 = buildCompleteTree(levelOrder);

        System.out.print("1. 前序+中序重建的中序走訪: ");
        inorderPrint(tree1);
        System.out.println();

        System.out.print("2. 後序+中序重建的中序走訪: ");
        inorderPrint(tree2);
        System.out.println();

        System.out.print("3. 層序重建的中序走訪: ");
        inorderPrint(tree3);
        System.out.println();
    }
}
