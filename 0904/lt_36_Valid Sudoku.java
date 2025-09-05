
class Solution {

    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int boxIndex = (i / 3) * 3 + (j / 3);

                    if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                        return false;
                    }

                    rows[i][num] = cols[j][num] = boxes[boxIndex][num] = true;
                }
            }
        }
        return true;
    }
}

/*
解題思路（Valid Sudoku）：
1) 使用三個 9x9 的布林矩陣 rows、cols、boxes：
   - rows[i][d] 表示第 i 列是否出現過數字 d+1
   - cols[j][d] 表示第 j 欄是否出現過數字 d+1
   - boxes[b][d] 表示第 b 個 3x3 宮格是否出現過數字 d+1，b = (i/3)*3 + (j/3)
2) 逐格掃描棋盤：
   - 若為 '.' 表示空格，跳過；
   - 否則將字元轉為索引 num = board[i][j] - '1'（0~8）。
   - 若 rows[i][num]、cols[j][num] 或 boxes[boxIndex][num] 任何一個已為 true，代表重複，直接回傳 false。
   - 否則將三者都標記為 true。
3) 全部掃描完無衝突即回傳 true。
時間複雜度 O(9*9) = O(1)；空間複雜度 O(1)（固定大小）。
 */
ㄋ
