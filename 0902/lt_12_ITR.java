
class Solution {

    public String intToRoman(int num) {
        // 定義所有可能的羅馬數字組合及其對應的數值，從大到小排列
        // 包含了特殊情況，例如 900 = "CM", 400 = "CD", 90 = "XC" 等
        final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder(); // 用來拼接最後的羅馬數字結果

        // 遍歷所有的數值-符號對
        for (int i = 0; i < values.length; ++i) {
            if (num == 0) // 如果 num 已經轉換完畢，就提前結束
            {
                break;
            }

            // 當前 num 還大於等於 values[i] 時，就一直減去並拼接對應的符號
            // 例如 num = 58，遇到 50 -> "L"，剩下 8，再遇到 5 -> "V"，剩下 3，最後補 "III"
            while (num >= values[i]) {
                sb.append(symbols[i]); // 拼接對應的羅馬符號
                num -= values[i];      // 減去對應的數值
            }
        }

        // 返回最終的羅馬數字
        return sb.toString();
    }
}
