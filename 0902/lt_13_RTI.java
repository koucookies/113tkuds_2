
import java.util.*;

class Solution {

    public int romanToInt(String s) {
        // 建立羅馬數字與整數的對應表
        Map<Character, Integer> map = Map.of(
                'I', 1, 'V', 5, 'X', 10,
                'L', 50, 'C', 100, 'D', 500, 'M', 1000
        );

        int result = 0; // 用來存最終的整數結果

        // 遍歷字串中每個羅馬數字符號
        for (int i = 0; i < s.length(); i++) {
            int curr = map.get(s.charAt(i)); // 當前字符的數值
            // 取得下一個字符的數值，若 i 已經在最後一位，則設為 0
            int next = (i + 1 < s.length()) ? map.get(s.charAt(i + 1)) : 0;

            // 判斷是否為「減法規則」
            // 例如 IV = 4，因為 I(1) < V(5)，所以要做 result -= 1
            if (curr < next) {
                result -= curr;
            } else {
                // 否則就直接加上當前數值
                result += curr;
            }
        }

        return result; // 回傳轉換後的整數
    }
}
