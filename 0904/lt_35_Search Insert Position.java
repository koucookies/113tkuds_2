
class Solution {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 標準二分搜尋法
        while (left <= right) {
            int mid = left + (right - left) / 2; // 避免 (left+right) 溢出

            if (nums[mid] == target) {
                return mid; // 若找到目標，直接回傳索引
            } else if (nums[mid] > target) {
                right = mid - 1; // 目標在左側
            } else {
                left = mid + 1; // 目標在右側
            }
        }

        // 若沒找到，left 會停在目標應插入的位置
        return left;
    }
}

/*
解題思路（Search Insert Position）：
1) 題目要求：找到 target 在排序陣列中的索引，如果不存在就返回應插入的位置。
2) 使用二分搜尋：
   - nums[mid] == target → 回傳 mid。
   - nums[mid] > target → 往左找。
   - nums[mid] < target → 往右找。
3) 當搜尋結束後，left 會指向最適合插入 target 的位置。
時間複雜度 O(log n)，空間複雜度 O(1)。
 */
ㄋ
