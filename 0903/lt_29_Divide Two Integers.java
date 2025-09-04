
class Solution {

    public int divide(int dividend, int divisor) {
        // 特殊情況處理：若被除數為最小值且除數為 -1，會溢出，回傳最大值
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 轉換成 long 並取絕對值，避免溢出
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        long quotient = 0; // 商

        // 當被除數大於等於除數時，持續減去除數的倍數
        while (dvd >= dvs) {
            long temp = dvs, multiple = 1; // temp 為當前除數的倍數，multiple 為對應的倍數
            // 不斷將除數倍增，直到超過被除數
            while (dvd >= (temp << 1)) {
                temp <<= 1; // 除數翻倍
                multiple <<= 1; // 倍數翻倍
            }
            dvd -= temp; // 減去最大可能的除數倍數
            quotient += multiple; // 商加上對應倍數
        }

        // 判斷結果正負號，若 dividend 與 divisor 其中一個為負數，則結果為負
        if ((dividend < 0) ^ (divisor < 0)) {
            return (int) -quotient;
        }

        return (int) quotient; // 回傳最終商
    }
}
