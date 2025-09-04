
class Solution {

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>(); // 存放所有可能的組合結果

        if (digits == null || digits.length() == 0) {
            return res; // 如果輸入為空，直接回傳空結果
        }

        // 建立數字到字母的映射表
        Map<Character, String> digitToLetters = new HashMap<>();
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");

        // 使用回溯法產生所有可能組合
        backtrack(digits, 0, new StringBuilder(), res, digitToLetters);

        return res; // 回傳最終的所有組合
    }

    private void backtrack(String digits, int idx, StringBuilder comb, List<String> res, Map<Character, String> digitToLetters) {
        if (idx == digits.length()) {
            res.add(comb.toString()); // 若組合長度等於輸入長度，存入結果
            return;
        }

        String letters = digitToLetters.get(digits.charAt(idx)); // 取得當前數字對應的字母
        for (char letter : letters.toCharArray()) { // 遍歷每個可能字母
            comb.append(letter); // 選擇當前字母
            backtrack(digits, idx + 1, comb, res, digitToLetters); // 遞歸處理下一位數字
            comb.deleteCharAt(comb.length() - 1); // 回溯，移除最後一個字母
        }
    }
}
