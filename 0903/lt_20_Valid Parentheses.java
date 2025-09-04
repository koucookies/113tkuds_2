
class Solution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>(); // 用來存放左括號，便於後續比對
        for (char ch : s.toCharArray()) { // 遍歷字串中的每個字元
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch); // 如果是左括號，推入堆疊
            } else {
                if (stack.isEmpty()) {
                    return false; // 如果遇到右括號但堆疊為空，代表不合法
                }
                char top = stack.pop(); // 取出堆疊頂端的左括號
                if (ch == ')' && top != '(') {
                    return false; // 括號不匹配
                }
                if (ch == ']' && top != '[') {
                    return false; // 括號不匹配
                }
                if (ch == '}' && top != '{') {
                    return false; // 括號不匹配
                }
            }
        }
        return stack.isEmpty(); // 最後堆疊必須為空，才代表所有括號正確匹配
    }
}
