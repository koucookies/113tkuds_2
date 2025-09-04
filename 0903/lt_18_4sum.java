
class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>(); // 存放所有符合條件的四元組
        Arrays.sort(nums); // 排序，方便去重與雙指標處理

        for (int i = 0; i < nums.length - 3; i++) { // 第一層迴圈，固定第一個數
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 避免重複解
            }
            for (int j = i + 1; j < nums.length - 2; j++) { // 第二層迴圈，固定第二個數
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // 避免重複解
                }
                int left = j + 1; // 左指標
                int right = nums.length - 1; // 右指標

                while (left < right) {
                    // 使用 long 防止整數加總溢出
                    long total = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];

                    if (total < target) {
                        left++; // 總和太小，移動左指標增加總和
                     }else if (total > target) {
                        right--; // 總和太大，移動右指標減少總和
                     }else {
                        // 找到一組符合條件的解
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;

                        // 跳過重複的數字，避免產生相同結果
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    }
                }
            }
        }
        return res; // 回傳所有不重複的四元組
    }
}
