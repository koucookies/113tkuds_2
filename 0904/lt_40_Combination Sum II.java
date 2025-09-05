
class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 先排序，便於剪枝與跳過重複
        List<List<Integer>> res = new ArrayList<>();

        dfs(candidates, target, 0, new ArrayList<Integer>(), res); // 回溯搜索
        return res;
    }

    private void dfs(int[] candidates, int target, int start, List<Integer> comb, List<List<Integer>> res) {
        if (target < 0) {
            return; // 超過目標，剪枝
        }

        if (target == 0) {
            res.add(new ArrayList<Integer>(comb)); // 剛好等於目標，加入答案
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue; // 同層去重：跳過相同數值以避免重複解
            }

            if (candidates[i] > target) {
                break; // 已排序，後面只會更大，直接剪枝
            }

            comb.add(candidates[i]); // 選擇當前數字
            dfs(candidates, target - candidates[i], i + 1, comb, res); // 元素每個最多使用一次，所以下一層從 i+1
            comb.remove(comb.size() - 1); // 回溯：撤銷選擇
        }
    }
}

/*
解題思路（Combination Sum II，回溯 + 去重）：
1) 排序 candidates，便於剪枝（若 candidates[i] > target 直接停止）與同層去重（相同值僅取第一個）。
2) 回溯參數：
   - target：剩餘目標值；
   - start：當前層起始索引；每個元素最多用一次，遞迴時傳 i+1；
   - comb：當前組合路徑。
3) 去重策略：
   - 同層（同一層 for 迴圈）若 i > start 且 candidates[i] == candidates[i-1]，則 continue 跳過，避免重複解。
4) 剪枝：
   - 若 candidates[i] > target（已排序），後續更大，直接 break；
   - 若 target < 0，返回。
5) 終止條件：
   - target == 0，收錄當前組合。
時間複雜度近似指數級；空間複雜度取決於遞迴深度與路徑長度。
 */
```
