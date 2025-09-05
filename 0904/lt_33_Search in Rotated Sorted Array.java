
class Solution {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 使用二分搜尋法
        while (left <= right) {
            int mid = (left + right) / 2; // 取中間索引

            if (nums[mid] == target) {
                return mid; // 找到目標值，回傳索引
            } // 如果左半部分有序
            else if (nums[mid] >= nums[left]) {
                // 判斷 target 是否落在左半部分區間
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1; // 往左半部分搜尋
                } else {
                    left = mid + 1; // 往右半部分搜尋
                }
            } // 如果右半部分有序
            else {
                // 判斷 target 是否落在右半部分區間
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1; // 往右半部分搜尋
                } else {
                    right = mid - 1; // 往左半部分搜尋
                }
            }
        }

        return -1; // 若找不到，回傳 -1
    }
}

/*
解題思路（Search in Rotated Sorted Array）：
1) 陣列被旋轉過，但至少有一半是有序的。
2) 每次取中點：
   - 若 nums[mid] == target → 回傳 mid。
   - 若左半邊有序 → 判斷 target 是否在區間 [nums[left], nums[mid]]。
   - 若右半邊有序 → 判斷 target 是否在區間 [nums[mid], nums[right]]。
3) 根據區間關係，調整左右指標繼續搜尋。
時間複雜度 O(log n)，空間複雜度 O(1)。
 */
