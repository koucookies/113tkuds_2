
class Solution {

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>(); // 用來存放索引，協助判斷合法括號的長度
        stack.push(-1); // 初始放入 -1，作為基準點
        int max_len = 0; // 紀錄最長合法括號長度

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i); // 左括號，將索引入棧
            } else {
                stack.pop(); // 嘗試匹配一個左括號
                if (stack.isEmpty()) {
                    stack.push(i); // 若棧為空，將當前右括號索引作為新的基準點
                } else {
                    // 當前長度 = 當前索引 - 棧頂索引
                    max_len = Math.max(max_len, i - stack.peek());
                }
            }
        }

        return max_len; // 回傳最長合法括號長度
    }
}

/*
解題思路（Longest Valid Parentheses）：
1) 使用一個 stack 紀錄索引，初始放入 -1 作為基準點。
2) 遇到 '(' 就把索引壓入 stack。
3) 遇到 ')' 就從 stack 彈出：
   - 若 stack 為空，表示沒有可以匹配的 '('，把當前索引設為基準。
   - 若 stack 不為空，合法區間長度 = 當前索引 - stack 頂端索引。
4) 在遍歷過程中不斷更新最大長度。
時間複雜度 O(n)，空間複雜度 O(n)。
 */
