
class Solution {

    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int closestSum = Integer.MAX_VALUE / 2; // 初始化答案為極大值的一半，避免溢出

        for (int i = 0; i < n; i++) { // 第一層迴圈，枚舉第一個數
            for (int j = i + 1; j < n; j++) { // 第二層迴圈，枚舉第二個數
                for (int k = j + 1; k < n; k++) { // 第三層迴圈，枚舉第三個數
                    int sum = nums[i] + nums[j] + nums[k]; // 計算三數和
                    if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                        closestSum = sum; // 若此組合更接近目標值，更新答案
                    }
                }
            }
        }
        return closestSum; // 回傳最接近目標的三數和
    }
}
