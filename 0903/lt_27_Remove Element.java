
class Solution {

    public int removeElement(int[] nums, int val) {
        int k = 0; // k 用來標記下一個要放置非 val 元素的位置

        for (int i = 0; i < nums.length; i++) { // 遍歷整個陣列
            if (nums[i] != val) { // 若當前元素不等於 val
                nums[k] = nums[i]; // 將此元素放到位置 k
                k++; // 移動 k，準備放下一個有效元素
            }
        }

        return k; // k 即為移除 val 後剩餘元素的數量
    }
}
ㄋ
