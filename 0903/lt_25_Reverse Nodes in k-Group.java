
class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null; // 空鏈結串列直接回傳
        }
        ListNode tail = head;
        // 檢查是否至少有 k 個節點可供反轉
        for (int i = 0; i < k; i++) {
            if (tail == null) {
                return head; // 不足 k 個，保持原樣

                        }tail = tail.next;
        }

        // 反轉區間 [head, tail)
        ListNode newHead = reverse(head, tail);
        // 原本 head 經過反轉後變成最後一個，接上遞迴結果
        head.next = reverseKGroup(tail, k);
        return newHead; // 回傳新的頭節點
    }

    private ListNode reverse(ListNode cur, ListNode end) {
        ListNode prev = null;
        // 反轉鏈結串列，直到到達 end（不包含 end）
        while (cur != end) {
            ListNode next = cur.next; // 暫存下一個節點
            cur.next = prev; // 當前節點指向前一個節點
            prev = cur; // 移動 prev
            cur = next; // 移動 cur
        }
        return prev; // 回傳反轉後的頭節點
    }
}
