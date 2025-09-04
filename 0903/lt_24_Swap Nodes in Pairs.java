
class Solution {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head); // 建立虛擬頭節點，方便處理頭節點交換
        ListNode prev = dummy, cur = head; // prev 指向前一個節點，cur 指向當前要交換的節點

        while (cur != null && cur.next != null) { // 只要當前節點與下一個節點存在，才能交換
            ListNode npn = cur.next.next; // 暫存下一對的起始節點
            ListNode second = cur.next; // 當前要交換的第二個節點

            second.next = cur; // 第二個節點指向第一個節點
            cur.next = npn; // 第一個節點接上後續的節點
            prev.next = second; // 前一個節點接上第二個節點（完成交換）

            prev = cur; // prev 移到下一組的前一個節點
            cur = npn; // cur 移到下一組的起始節點
        }

        return dummy.next; // 回傳新的頭節點
    }
}
