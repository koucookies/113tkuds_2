
class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>(); // 存放所有合法括號組合

        dfs(0, 0, "", n, res); // 從 0 左括號與 0 右括號開始遞歸

        return res;
    }

    private void dfs(int openP, int closeP, String s, int n, List<String> res) {
        // 若左右括號數量相等且總數達到 2n，表示生成了一個合法組合
        if (openP == closeP && openP + closeP == n * 2) {
            res.add(s);
            return;
        }

        // 只要左括號數量小於 n，就可以繼續加入左括號
        if (openP < n) {
            dfs(openP + 1, closeP, s + "(", n, res);
        }

        // 只要右括號數量小於左括號，就可以加入右括號
        if (closeP < openP) {
            dfs(openP, closeP + 1, s + ")", n, res);
        }
    }
}
