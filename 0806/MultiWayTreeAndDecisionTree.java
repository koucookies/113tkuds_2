
import java.util.*;

class MultiWayTreeNode {

    String value;
    List<MultiWayTreeNode> children;

    public MultiWayTreeNode(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(MultiWayTreeNode child) {
        this.children.add(child);
    }
}

public class MultiWayTreeAndDecisionTree {

    // 1. 深度優先走訪
    public static void dfs(MultiWayTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        for (MultiWayTreeNode child : node.children) {
            dfs(child);
        }
    }

    // 2. 廣度優先走訪
    public static void bfs(MultiWayTreeNode root) {
        if (root == null) {
            return;
        }
        Queue<MultiWayTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiWayTreeNode node = queue.poll();
            System.out.print(node.value + " ");
            for (MultiWayTreeNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    // 3. 計算高度
    public static int calculateHeight(MultiWayTreeNode node) {
        if (node == null) {
            return 0;
        }
        int maxChildHeight = 0;
        for (MultiWayTreeNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, calculateHeight(child));
        }
        return maxChildHeight + 1;
    }

    // 4. 計算每個節點的度數
    public static void printDegrees(MultiWayTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println("節點 " + node.value + " 的度數: " + node.children.size());
        for (MultiWayTreeNode child : node.children) {
            printDegrees(child);
        }
    }

    // 5. 模擬決策樹（猜數字）
    public static MultiWayTreeNode buildDecisionTree() {
        MultiWayTreeNode root = new MultiWayTreeNode("是偶數嗎?");
        MultiWayTreeNode yes = new MultiWayTreeNode("大於5嗎?");
        MultiWayTreeNode no = new MultiWayTreeNode("小於3嗎?");
        root.addChild(yes);
        root.addChild(no);

        yes.addChild(new MultiWayTreeNode("猜：8"));
        yes.addChild(new MultiWayTreeNode("猜：6"));

        no.addChild(new MultiWayTreeNode("猜：1"));
        no.addChild(new MultiWayTreeNode("猜：3"));

        return root;
    }

    public static void main(String[] args) {
        // 建立一般多路樹
        MultiWayTreeNode root = new MultiWayTreeNode("A");
        MultiWayTreeNode b = new MultiWayTreeNode("B");
        MultiWayTreeNode c = new MultiWayTreeNode("C");
        MultiWayTreeNode d = new MultiWayTreeNode("D");
        MultiWayTreeNode e = new MultiWayTreeNode("E");
        MultiWayTreeNode f = new MultiWayTreeNode("F");
        MultiWayTreeNode g = new MultiWayTreeNode("G");

        root.addChild(b);
        root.addChild(c);
        root.addChild(d);
        b.addChild(e);
        b.addChild(f);
        d.addChild(g);

        System.out.println("深度優先走訪:");
        dfs(root);
        System.out.println("\n廣度優先走訪:");
        bfs(root);

        System.out.println("\n\n每個節點的度數:");
        printDegrees(root);

        System.out.println("\n樹的高度: " + calculateHeight(root));

        // 模擬決策樹（猜數字）
        System.out.println("\n模擬猜數字決策樹:");
        MultiWayTreeNode decisionTree = buildDecisionTree();
        dfs(decisionTree); // 可以用 DFS 看整棵決策邏輯
    }
}
