
class Solution {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0; // 若陣列為空，直接回傳 0
        }
        int i = 1; // i 指向下個應該放置非重複元素的位置

        for (int j = 1; j < nums.length; j++) { // j 用來遍歷整個陣列
            if (nums[j] != nums[i - 1]) { // 若當前元素與前一個不重複
                nums[i] = nums[j]; // 將 nums[j] 放到 i 的位置
                i++; // 移動 i 到下一個位置
            }
        }

        return i; // i 即為不重複元素的數量
    }
}
