
import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {

        int val;
        char color; // 'R' or 'B'
        int index;
        Node left, right;

        Node(int val, char color, int index) {
            this.val = val;
            this.color = color;
            this.index = index;
        }
    }

    static Node[] nodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            char c = sc.next().charAt(0);
            if (val != -1) {
                nodes[i] = new Node(val, c, i);
            }
        }

        if (nodes[0] == null || nodes[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        buildTree();

        int result = checkProperties(nodes[0]);
        if (result == -1) {
            // Black height mismatch is already printed inside function
        } else {
            System.out.println("RB Valid");
        }
    }

    // 根據 index 建立左右子樹連結
    static void buildTree() {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }
            int leftIdx = 2 * i + 1;
            int rightIdx = 2 * i + 2;
            if (leftIdx < nodes.length) {
                nodes[i].left = nodes[leftIdx];
            }
            if (rightIdx < nodes.length) {
                nodes[i].right = nodes[rightIdx];
            }
        }
    }

    // 回傳子樹的黑高度；若錯誤則回傳 -1
    static int checkProperties(Node node) {
        if (node == null) {
            return 1; // 空節點黑高度為 1
        }
        // 紅紅相鄰檢查
        if (node.color == 'R') {
            if ((node.left != null && node.left.color == 'R')
                    || (node.right != null && node.right.color == 'R')) {
                System.out.println("RedRedViolation at index " + node.index);
                return -1;
            }
        }

        int leftHeight = checkProperties(node.left);
        int rightHeight = checkProperties(node.right);

        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        if (leftHeight != rightHeight) {
            System.out.println("BlackHeightMismatch");
            return -1;
        }

        return node.color == 'B' ? leftHeight + 1 : leftHeight;
    }
}
