
public class TreeMirrorAndSymmetry {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 判斷是否為對稱樹
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    // 2 & 3 用的：判斷兩棵樹是否為鏡像
    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    // 2. 將樹轉換為鏡像樹
    public static TreeNode mirror(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirror(root.left);
        TreeNode right = mirror(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // 4. 檢查子樹
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.val == b.val
                && isSameTree(a.left, b.left)
                && isSameTree(a.right, b.right);
    }

    public static void printInorder(TreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }
    }

    public static void main(String[] args) {
        // 範例對稱樹
        //      1
        //     / \
        //    2   2
        //   / \ / \
        //  3  4 4  3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹：" + isSymmetric(root));

        TreeNode mirrorTree = mirror(root);
        System.out.print("鏡像後中序走訪：");
        printInorder(mirrorTree);
        System.out.println();

        // 測試兩棵樹是否互為鏡像
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(3);

        TreeNode tree2 = new TreeNode(1);
        tree2.left = new TreeNode(3);
        tree2.right = new TreeNode(2);

        System.out.println("兩棵樹是否互為鏡像：" + isMirror(tree1, tree2));

        // 測試子樹
        TreeNode sub = new TreeNode(2);
        sub.left = new TreeNode(3);
        sub.right = new TreeNode(4);

        System.out.println("sub 是否為 root 的子樹：" + isSubtree(root, sub));
    }
}
