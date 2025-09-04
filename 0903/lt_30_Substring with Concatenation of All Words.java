
class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>(); // 存放所有符合條件的起始索引
        if (s == null || words == null || words.length == 0) {
            return result; // 特殊情況處理
        }
        int wordLen = words[0].length(); // 每個單字的長度
        int totalLen = wordLen * words.length; // 所有單字連接起來的總長度
        int sLen = s.length();

        // 統計 words 中每個單字出現的次數
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // 因為子字串可能從任意 wordLen 偏移開始，所以要遍歷 wordLen 種情況
        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i, count = 0; // 雙指標與計數器
            Map<String, Integer> window = new HashMap<>(); // 紀錄當前視窗內單字的出現次數

            // 使用滑動窗口，每次擴展 wordLen
            while (right + wordLen <= sLen) {
                String word = s.substring(right, right + wordLen); // 擷取當前單字
                right += wordLen;

                if (wordCount.containsKey(word)) { // 如果是目標單字
                    window.put(word, window.getOrDefault(word, 0) + 1);
                    count++;

                    // 若某個單字出現次數超過需求，移動左指標縮小視窗
                    while (window.get(word) > wordCount.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // 若窗口內單字數量等於 words 數量，代表找到一個有效子字串
                    if (count == words.length) {
                        result.add(left);
                    }
                } else {
                    // 若遇到不在 words 中的單字，重置視窗
                    window.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result; // 回傳所有符合條件的起始索引
    }
}
