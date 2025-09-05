
class Solution {

    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }
        if (i == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int j = nums.length - 1;
        while (j >= i && nums[j] <= nums[i - 1]) {
            j--;
        }
        swap(nums, i - 1, j);
        reverse(nums, i, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

/*
解題思路（Next Permutation）：
1) 從右往左找到第一個「下降」的位置 i-1（nums[i-1] < nums[i]），這個 i-1 是需要變動的樞紐。
2) 若找不到（i 退到 0），代表整個序列為非遞增序（最大排列），直接整段反轉為最小排列後返回。
3) 再從右往左找到第一個大於 nums[i-1] 的元素 j，與 nums[i-1] 交換，讓數值「盡可能小地變大」。
4) 最後將區間 [i, n-1] 反轉成遞增，確保得到「下一個」且是最小的更大排列。
複雜度：時間 O(n)，空間 O(1)。
 */
```
