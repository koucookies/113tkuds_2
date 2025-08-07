
public class BSTConversionAndBalance {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. BST -> 排序雙向串列
    static class DoublyListNode {

        int val;
        DoublyListNode prev, next;

        DoublyListNode(int val) {
            this.val = val;
        }
    }

    static DoublyListNode head = null, prev = null;

    public static DoublyListNode bstToDoublyList(TreeNode root) {
        head = null;
        prev = null;
        inOrderConvert(root);
        return head;
    }

    private static void inOrderConvert(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderConvert(node.left);
        DoublyListNode curr = new DoublyListNode(node.val);
        if (prev != null) {
            prev.next = curr;
            curr.prev = prev;
        } else {
            head = curr;
        }
        prev = curr;
        inOrderConvert(node.right);
    }

    // 2. Sorted Array -> Balanced BST
    public static TreeNode sortedArrayToBST(int[] arr) {
        return buildBST(arr, 0, arr.length - 1);
    }

    private static TreeNode buildBST(int[] arr, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = buildBST(arr, left, mid - 1);
        root.right = buildBST(arr, mid + 1, right);
        return root;
    }

    // 3. 判斷是否平衡，並印出每個節點平衡因子
    static boolean isBalanced = true;

    public static boolean checkBalanced(TreeNode root) {
        isBalanced = true;
        checkHeight(root);
        return isBalanced;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = checkHeight(node.left);
        int right = checkHeight(node.right);
        int balanceFactor = left - right;
        System.out.println("節點 " + node.val + " 的平衡因子: " + balanceFactor);
        if (Math.abs(balanceFactor) > 1) {
            isBalanced = false;
        }
        return Math.max(left, right) + 1;
    }

    // 4. 將 BST 中節點值改為 >= 自身值的總和
    static int sum = 0;

    public static void convertToGreaterSum(TreeNode node) {
        if (node == null) {
            return;
        }
        convertToGreaterSum(node.right);
        sum += node.val;
        node.val = sum;
        convertToGreaterSum(node.left);
    }

    // 中序列印
    public static void inorderPrint(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    public static void printDoublyList(DoublyListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        // 1. BST → 雙向鏈結串列
        System.out.print("1. 排序雙向串列：");
        DoublyListNode list = bstToDoublyList(root);
        printDoublyList(list);

        // 2. 陣列 → 平衡 BST
        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode bst = sortedArrayToBST(sorted);
        System.out.print("2. 陣列轉BST中序列印：");
        inorderPrint(bst);
        System.out.println();

        // 3. 是否為平衡 BST
        System.out.println("3. 是否平衡 BST：" + checkBalanced(bst));

        // 4. 轉換為 greater sum tree
        convertToGreaterSum(root);
        System.out.print("4. Greater Sum Tree 中序列印：");
        inorderPrint(root);
        System.out.println();
    }
}
