
import java.util.*;

public class LC21_MergeTwoLists_Clinics {

    static class ListNode {

        long val;
        ListNode next;

        ListNode(long v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();

        ListNode head1 = buildList(sc, n);
        ListNode head2 = buildList(sc, m);
        sc.close();

        ListNode merged = mergeTwoLists(head1, head2);

        // 輸出合併後序列
        StringBuilder out = new StringBuilder();
        while (merged != null) {
            if (out.length() > 0) {
                out.append(' ');
            }
            out.append(merged.val);
            merged = merged.next;
        }
        System.out.println(out.toString());
    }

    private static ListNode buildList(Scanner sc, int len) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < len; i++) {
            cur.next = new ListNode(sc.nextLong());
            cur = cur.next;
        }
        return dummy.next;
    }

    public static ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        // 掛上剩餘
        tail.next = (a != null) ? a : b;

        return dummy.next;
    }
}
