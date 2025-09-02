
vclass Solution {
    public int maxArea(int[] height) {
        int maxArea = 0; // 用來記錄目前找到的最大容器面積
        int left = 0; // 左指標，從陣列最左邊開始
        int right = height.length - 1; // 右指標，從陣列最右邊開始

        // 使用雙指標法，當左指標小於右指標時持續迭代
        while (left < right) {
            // 計算當前容器的面積：寬度 * 高度
            // 寬度 = right - left
            // 高度取決於左右指標中較小的高度
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));

            // 移動指標的策略：
            // 因為容器的高度取決於較短的邊，
            // 若左邊較短，移動左指標希望找到更高的邊來增加可能的面積
            // 若右邊較短，則移動右指標
            if (height[left] < height[right]) {
                left++; // 嘗試增加左邊高度
            } else {
                right--; // 嘗試增加右邊高度
            }
        }

        // 最終回傳最大面積
        return maxArea;        
    }
}
