
class Solution {

    public String longestCommonPrefix(String[] v) {
        StringBuilder ans = new StringBuilder(); // 用來存放最終的共同前綴

        // 先將字串陣列排序，這樣最小字串和最大字串之間的差異會最大化
        Arrays.sort(v);

        // 排序後，只需要比較陣列的第一個字串 (字典序最小) 和最後一個字串 (字典序最大)
        // 因為它們的共同前綴就是整個陣列的共同前綴
        String first = v[0];
        String last = v[v.length - 1];

        // 遍歷 first 和 last，逐字比較
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                // 一旦遇到不同的字元，直接回傳已經找到的共同前綴
                return ans.toString();
            }
            // 若相同，加入結果
            ans.append(first.charAt(i));
        }

        // 如果迴圈結束都沒有不同字元，代表最短字串本身就是共同前綴
        return ans.toString();
    }
}
