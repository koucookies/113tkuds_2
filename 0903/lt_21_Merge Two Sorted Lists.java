
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(); // 建立虛擬頭節點，方便處理
        ListNode temp = res; // 指標用於連接新串列

        // 當兩個鏈結串列都不為空時，逐一比較節點值
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = new ListNode(list1.val); // 選取 list1 的節點
                list1 = list1.next; // 移動 list1 指標
            } else {
                temp.next = new ListNode(list2.val); // 選取 list2 的節點
                list2 = list2.next; // 移動 list2 指標
            }
            temp = temp.next; // 移動 temp 指標
        }

        // 若 list1 還有剩餘，直接接到結果後面
        if (list1 != null) {
            temp.next = list1;
        }
        // 若 list2 還有剩餘，直接接到結果後面
        if (list2 != null) {
            temp.next = list2;
        }

        return res.next; // 回傳合併後的鏈結串列（跳過虛擬頭節點）
    }
}
