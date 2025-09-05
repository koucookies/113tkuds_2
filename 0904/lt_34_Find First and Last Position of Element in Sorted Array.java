
class Solution {

    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1}; // 預設為 [-1, -1]，若找不到 target 就回傳這個
        int left = binarySearch(nums, target, true); // 找最左邊的 target
        int right = binarySearch(nums, target, false); // 找最右邊的 target
        result[0] = left;
        result[1] = right;
        return result;
    }

    private int binarySearch(int[] nums, int target, boolean isSearchingLeft) {
        int left = 0;
        int right = nums.length - 1;
        int idx = -1; // 紀錄找到 target 的索引

        while (left <= right) {
            int mid = left + (right - left) / 2; // 避免溢出的中點計算

            if (nums[mid] > target) {
                right = mid - 1; // target 在左側
            } else if (nums[mid] < target) {
                left = mid + 1; // target 在右側
            } else {
                idx = mid; // 找到 target
                if (isSearchingLeft) {
                    right = mid - 1; // 繼續往左找，鎖定最左邊的 target
                } else {
                    left = mid + 1; // 繼續往右找，鎖定最右邊的 target
                }
            }
        }

        return idx; // 回傳找到的索引（若沒找到則為 -1）
    }
}

/*
解題思路（Find First and Last Position of Element in Sorted Array）：
1) 題目要求找到 target 的起始與結束索引。
2) 使用二分搜尋法做兩次：
   - 第一次尋找最左邊的 target。
   - 第二次尋找最右邊的 target。
3) 若找到則更新索引，若找不到保持 -1。
時間複雜度 O(log n)，空間複雜度 O(1)。
 */
