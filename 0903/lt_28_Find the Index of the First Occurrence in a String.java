
class Solution {

    public int strStr(String haystack, String needle) {
        // 使用滑動窗口檢查 needle 是否出現在 haystack 中
        for (int i = 0, j = needle.length(); j <= haystack.length(); i++, j++) {
            // 取子字串 [i, j)，並比較是否等於 needle
            if (haystack.substring(i, j).equals(needle)) {
                return i; // 若相同，回傳起始索引 i
            }
        }
        return -1; // 若找不到 needle，回傳 -1
    }
}
