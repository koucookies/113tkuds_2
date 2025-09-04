
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0; // 用來計算鏈結串列的長度
        ListNode curr = head;
        ListNode dummy = new ListNode(0); // 建立虛擬節點，處理刪除頭節點的特殊情況
        dummy.next = head;
        ListNode prev = dummy;

        // 第一次遍歷：計算總節點數
        while (curr != null) {
            count += 1;
            curr = curr.next;
        }

        curr = head; // 重置 curr 到頭節點
        int nodetoRemovePos = count - n; // 需要刪除的節點位置（從前往後數）
        int secondPass = 0;

        // 第二次遍歷：移動到要刪除節點的前一個位置
        while (secondPass < nodetoRemovePos) {
            prev = curr;
            curr = curr.next;
            secondPass += 1;
        }

        // 刪除目標節點
        prev.next = curr.next;

        return dummy.next; // 回傳新的頭節點
    }
}
