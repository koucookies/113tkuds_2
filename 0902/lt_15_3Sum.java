
import java.util.*;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>(); // 用來存放所有不重複的三元組
        Arrays.sort(nums); // 先排序，方便用雙指標並避免重複組合

        // 固定一個數 nums[i]，然後用雙指標在右邊區間尋找兩數，使三者之和為 0
        for (int i = 0; i < nums.length - 2; i++) {
            // 避免重複計算：如果當前數字和前一個一樣，就跳過
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1, right = nums.length - 1; // 左右指標
            while (left < right) {
                int total = nums[i] + nums[left] + nums[right]; // 當前三數之和

                if (total == 0) {
                    // 找到一組答案
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳過重複的 left 數值，避免同樣組合被加入多次
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳過重複的 right 數值
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // 繼續縮小範圍
                    left++;
                    right--;
                } else if (total < 0) {
                    // 總和太小，需要增加 → 移動 left
                    left++;
                } else {
                    // 總和太大，需要減少 → 移動 right
                    right--;
                }
            }
        }

        return res; // 回傳所有找到的不重複三元組
    }
}
