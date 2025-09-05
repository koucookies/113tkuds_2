
import java.util.*;

public class LC25_ReverseKGroup_Shifts {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        // 建立鏈結串列
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (sc.hasNextInt()) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        sc.close();

        ListNode head = dummy.next;
        ListNode newHead = reverseKGroup(head, k);

        // 輸出
        StringBuilder out = new StringBuilder();
        cur = newHead;
        while (cur != null) {
            if (out.length() > 0) {
                out.append(' ');
            }
            out.append(cur.val);
            cur = cur.next;
        }
        System.out.println(out.toString());
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            // 找到當前組的末端
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) {
                break; // 剩餘不足 k

                        }ListNode groupNext = kth.next;

            // 反轉該組
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // 調整前後指標
            ListNode tmp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = tmp;
        }

        return dummy.next;
    }

    private static ListNode getKth(ListNode start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }
        return start;
    }
}
