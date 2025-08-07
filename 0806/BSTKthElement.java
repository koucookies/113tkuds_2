
import java.util.*;

public class BSTKthElement {

    static class TreeNode {

        int val, size; // size: 子樹節點數（包含自己）
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    // 更新子樹節點數
    static int getSize(TreeNode node) {
        return node == null ? 0 : node.size;
    }

    static void updateSize(TreeNode node) {
        if (node != null) {
            node.size = 1 + getSize(node.left) + getSize(node.right);
        }
    }

    // 插入節點
    static TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val); 
        }else {
            root.right = insert(root.right, val);
        }
        updateSize(root);
        return root;
    }

    // 刪除節點
    static TreeNode delete(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val < root.val) {
            root.left = delete(root.left, val);
        } else if (val > root.val) {
            root.right = delete(root.right, val);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode successor = findMin(root.right);
            root.val = successor.val;
            root.right = delete(root.right, successor.val);
        }
        updateSize(root);
        return root;
    }

    static TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 1. 找第 k 小元素
    static int findKthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        int leftSize = getSize(root.left);
        if (k == leftSize + 1) {
            return root.val; 
        }else if (k <= leftSize) {
            return findKthSmallest(root.left, k); 
        }else {
            return findKthSmallest(root.right, k - leftSize - 1);
        }
    }

    // 2. 找第 k 大元素（等於找第 size-k+1 小）
    static int findKthLargest(TreeNode root, int k) {
        int total = getSize(root);
        return findKthSmallest(root, total - k + 1);
    }

    // 3. 找第 k 到第 m 小元素
    static void findRange(TreeNode root, int k, int m, List<Integer> result) {
        findRangeHelper(root, new int[]{0}, k, m, result);
    }

    static void findRangeHelper(TreeNode node, int[] count, int k, int m, List<Integer> result) {
        if (node == null) {
            return;
        }

        findRangeHelper(node.left, count, k, m, result);

        count[0]++;
        if (count[0] >= k && count[0] <= m) {
            result.add(node.val);
        }
        if (count[0] > m) {
            return;
        }

        findRangeHelper(node.right, count, k, m, result);
    }

    // 測試
    public static void main(String[] args) {
        int[] nums = {20, 10, 5, 15, 30, 25, 35};
        TreeNode root = null;
        for (int val : nums) {
            root = insert(root, val);
        }

        System.out.println("第 3 小元素: " + findKthSmallest(root, 3)); // 預期 15
        System.out.println("第 2 大元素: " + findKthLargest(root, 2));   // 預期 30

        List<Integer> range = new ArrayList<>();
        findRange(root, 2, 5, range);
        System.out.println("第 2 到第 5 小元素: " + range); // 預期 [10, 15, 20, 25]

        root = insert(root, 12);
        root = delete(root, 25);
        System.out.println("插入 12 並刪除 25 後，第 3 小元素: " + findKthSmallest(root, 3)); // 預期 12
    }
}
