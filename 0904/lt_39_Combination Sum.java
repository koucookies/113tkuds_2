
class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        makeCombination(candidates, target, 0, new ArrayList<>(), 0, res);
        return res;
    }

    private void makeCombination(int[] candidates, int target, int idx, List<Integer> comb, int total, List<List<Integer>> res) {
        if (total == target) {
            res.add(new ArrayList<>(comb));
            return;
        }

        if (total > target || idx >= candidates.length) {
            return;
        }

        comb.add(candidates[idx]);
        makeCombination(candidates, target, idx, comb, total + candidates[idx], res);
        comb.remove(comb.size() - 1);
        makeCombination(candidates, target, idx + 1, comb, total, res);
    }
}

/*
解題思路（Combination Sum，回溯）：
1) 狀態與選擇：
   - idx：目前考慮到第 idx 個候選數。
   - comb：當前組合。
   - total：當前組合的總和。
   - 對於 candidates[idx] 有兩種選擇：
     a) 使用它（可重複使用），total += candidates[idx]，idx 不變；
     b) 不使用它，idx + 1 移到下一個候選數。
2) 終止條件：
   - total == target：找到一個可行解，加入結果。
   - total > target 或 idx 越界：剪枝返回。
3) 正確性：
   - 透過「使用 / 不使用」的分支，窮舉所有可能組合；
   - 使用同一元素時保持 idx 不變，確保可無限次使用；
   - 往下一個元素時 idx + 1，避免出現排列重複。
4) 複雜度：
   - 時間：最壞情況指數級（依 target 與 candidates 而定）。
   - 空間：遞迴深度與路徑儲存為 O(target / min(candidates))（忽略輸出）。
 */
```
