
import java.util.*;

public class LC23_MergeKLists_Hospitals {

    static class ListNode {

        long val;
        ListNode next;

        ListNode(long v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        // 讀入 k 條串列（每行以 -1 結束；可能為空串列：直接 -1）
        List<ListNode> heads = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            heads.add(readList(sc));
        }
        sc.close();

        // 用最小堆合併
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.val));
        for (ListNode h : heads) {
            if (h != null) {
                pq.offer(h);
            }
        }

        StringBuilder out = new StringBuilder();
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            if (out.length() > 0) {
                out.append(' ');
            }
            out.append(cur.val);
            if (cur.next != null) {
                pq.offer(cur.next);
            }
        }

        // 輸出合併後序列（若全部為空，輸出空行）
        System.out.println(out.toString());
    }

    // 讀取一條以 -1 結尾的升序序列；可能為空（第一個數即為 -1）
    private static ListNode readList(Scanner sc) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (true) {
            long x = sc.nextLong();
            if (x == -1) {
                break;
            }
            tail.next = new ListNode(x);
            tail = tail.next;
        }
        return dummy.next;
    }
}
