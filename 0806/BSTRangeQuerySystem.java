
public class BSTRangeQuerySystem {

    static class TreeNode {

        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // 插入節點
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.data) {
            root.left = insert(root.left, val); 
        }else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    // 1. 範圍查詢：列出 [min, max] 範圍的所有節點
    public static void rangeQuery(TreeNode root, int min, int max) {
        if (root == null) {
            return;
        }

        if (root.data > min) {
            rangeQuery(root.left, min, max);
        }
        if (root.data >= min && root.data <= max) {
            System.out.print(root.data + " ");
        }
        if (root.data < max) {
            rangeQuery(root.right, min, max);
        }
    }

    // 2. 範圍計數
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) {
            return 0;
        }
        if (root.data < min) {
            return rangeCount(root.right, min, max);
        }
        if (root.data > max) {
            return rangeCount(root.left, min, max);
        }
        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    // 3. 範圍總和
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) {
            return 0;
        }
        if (root.data < min) {
            return rangeSum(root.right, min, max);
        }
        if (root.data > max) {
            return rangeSum(root.left, min, max);
        }
        return root.data + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    // 4. 最接近指定值的節點
    public static int closestValue(TreeNode root, int target) {
        int closest = root.data;
        while (root != null) {
            if (Math.abs(target - root.data) < Math.abs(target - closest)) {
                closest = root.data;
            }
            root = (target < root.data) ? root.left : root.right;
        }
        return closest;
    }

    public static void main(String[] args) {
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }

        int min = 12, max = 28, target = 17;

        System.out.print("範圍查詢 [" + min + "," + max + "]：");
        rangeQuery(root, min, max);
        System.out.println();

        System.out.println("範圍計數：" + rangeCount(root, min, max));
        System.out.println("範圍總和：" + rangeSum(root, min, max));
        System.out.println("最接近 " + target + " 的節點：" + closestValue(root, target));
    }
}
