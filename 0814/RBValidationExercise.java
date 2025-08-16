
public class RBValidationExercise {

    static class Node {

        int val;
        boolean isRed; // true = red, false = black
        Node left, right;

        Node(int val, boolean isRed) {
            this.val = val;
            this.isRed = isRed;
        }
    }

    static class ValidationResult {

        boolean isValid;
        String message;

        ValidationResult(boolean isValid, String message) {
            this.isValid = isValid;
            this.message = message;
        }
    }

    public static ValidationResult validateRedBlackTree(Node root) {
        if (root == null) {
            return new ValidationResult(true, "空樹視為有效紅黑樹");
        }

        if (root.isRed) {
            return new ValidationResult(false, "根節點不是黑色");
        }

        return checkProperties(root);
    }

    private static ValidationResult checkProperties(Node node) {
        return checkNode(node);
    }

    private static ValidationResult checkNode(Node node) {
        if (node == null) {
            return new ValidationResult(true, "OK"); // null 視為黑節點
        }

        // 性質 4：紅節點不能有紅小孩
        if (node.isRed) {
            if ((node.left != null && node.left.isRed) || (node.right != null && node.right.isRed)) {
                return new ValidationResult(false, "紅節點有紅色子節點: " + node.val);
            }
        }

        // 遞迴檢查左右子樹
        ValidationResult leftResult = checkNode(node.left);
        if (!leftResult.isValid) {
            return leftResult;
        }

        ValidationResult rightResult = checkNode(node.right);
        if (!rightResult.isValid) {
            return rightResult;
        }

        // 黑高度一致性檢查
        int leftBlackHeight = getBlackHeight(node.left);
        int rightBlackHeight = getBlackHeight(node.right);

        if (leftBlackHeight != rightBlackHeight) {
            return new ValidationResult(false, "黑高度不一致 at node: " + node.val);
        }

        return new ValidationResult(true, "OK");
    }

    private static int getBlackHeight(Node node) {
        if (node == null) {
            return 1; // null node = black
        }
        int left = getBlackHeight(node.left);
        int right = getBlackHeight(node.right);

        // 若不一致，傳最小值，讓上層檢查時能抓到錯誤
        if (left != right) {
            return Math.min(left, right);
        }

        return left + (node.isRed ? 0 : 1);
    }

    public static void main(String[] args) {
        // 測試：一個合法的紅黑樹
        Node root = new Node(10, false);
        root.left = new Node(5, true);
        root.right = new Node(15, true);
        root.left.left = new Node(2, false);
        root.left.right = new Node(7, false);
        root.right.right = new Node(20, false);

        ValidationResult result = validateRedBlackTree(root);
        System.out.println("結果: " + result.isValid + "，原因: " + result.message);

        // 測試：根節點紅色，違反性質 2
        Node invalidRoot = new Node(10, true);
        result = validateRedBlackTree(invalidRoot);
        System.out.println("結果: " + result.isValid + "，原因: " + result.message);
    }
}
