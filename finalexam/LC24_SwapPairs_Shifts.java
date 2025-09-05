
import java.util.*;

public class LC24_SwapPairs_Shifts {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 讀取整行輸入
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (sc.hasNextInt()) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        sc.close();

        ListNode head = dummy.next;
        ListNode newHead = swapPairs(head);

        // 輸出結果
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

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換 (a, b)
            a.next = b.next;
            b.next = a;
            prev.next = b;

            // 移動 prev
            prev = a;
        }

        return dummy.next;
    }
}
