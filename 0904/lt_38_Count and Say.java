
class Solution {

    public String countAndSay(int n) {
        String res = "1"; // 初始序列
        for (int i = 1; i < n; i++) { // 迭代 n-1 次，生成下一個序列
            StringBuilder temp = new StringBuilder(); // 暫存新序列
            int count = 1; // 計算相同數字的個數
            for (int j = 1; j < res.length(); j++) {
                if (res.charAt(j) == res.charAt(j - 1)) {
                    count++; // 相同數字，累加計數
                } else {
                    // 遇到不同數字，將之前的數字組合輸出到 temp
                    temp.append(count).append(res.charAt(j - 1));
                    count = 1; // 重置計數器
                }
            }
            // 處理最後一段數字
            temp.append(count).append(res.charAt(res.length() - 1));
            res = temp.toString(); // 更新 res 為下一個序列
        }
        return res; // 回傳第 n 個序列
    }
}

/*
解題思路（Count and Say）：
1) 題意：每一項是前一項的「讀法」。
   - "1" → "一個 1" → "11"
   - "11" → "兩個 1" → "21"
   - "21" → "一個 2，一個 1" → "1211"
2) 演算法：
   - 從 res="1" 開始，迭代 n-1 次。
   - 使用雙指標或計數器，遍歷 res，計算連續相同數字的數量。
   - 將「count + 數字」拼接進 temp。
   - 更新 res = temp。
3) 時間複雜度：
   - 每一輪遍歷 res 長度 O(m)，總時間約 O(n*m)。
   - 空間複雜度 O(m)，主要用於建構新字串。
 */
