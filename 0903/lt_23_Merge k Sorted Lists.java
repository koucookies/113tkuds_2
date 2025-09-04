
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null; // 若輸入為空或沒有任何鏈結串列，直接回傳 null
        }
        return mergeKListsHelper(lists, 0, lists.length - 1); // 使用分治法合併
    }

    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start]; // 區間內只有一個鏈結串列，直接回傳
        }
        if (start + 1 == end) {
            return merge(lists[start], lists[end]); // 區間內只有兩個鏈結串列，直接合併
        }
        int mid = start + (end - start) / 2; // 找到中間點，進行分治
        ListNode left = mergeKListsHelper(lists, start, mid); // 合併左半部分
        ListNode right = mergeKListsHelper(lists, mid + 1, end); // 合併右半部分
        return merge(left, right); // 合併左右結果
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // 虛擬頭節點，簡化鏈結處理
        ListNode curr = dummy;

        // 兩條鏈結串列逐一比較，取小的接到結果串列
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        // 若其中一條還有剩餘，直接接到結果後面
        curr.next = (l1 != null) ? l1 : l2;

        return dummy.next; // 回傳合併後的串列，跳過虛擬頭節點
    }
}
