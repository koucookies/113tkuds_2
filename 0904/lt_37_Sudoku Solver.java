
class Solution {

    int n = 3;
    int N = n * n;
    int[][] rows = new int[N][N + 1];
    int[][] columns = new int[N][N + 1];
    int[][] boxes = new int[N][N + 1];
    char[][] board;
    boolean sudokuSolved = false;

    public boolean couldPlace(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }

    public void placeNumber(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        board[row][col] = (char) (d + '0');
    }

    public void removeNumber(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        board[row][col] = '.';
    }

    public void placeNextNumbers(int row, int col) {
        if (row == N - 1 && col == N - 1) {
            sudokuSolved = true; 
        }else if (col == N - 1) {
            backtrack(row + 1, 0); 
        }else {
            backtrack(row, col + 1);
        }
    }

    public void backtrack(int row, int col) {
        if (board[row][col] == '.') {
            for (int d = 1; d <= 9; d++) {
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    if (!sudokuSolved) {
                        removeNumber(d, row, col);
                    }
                }
            }
        } else {
            placeNextNumbers(row, col);
        }
    }

    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != '.') {
                    placeNumber(Character.getNumericValue(board[i][j]), i, j);
                }
            }
        }
        backtrack(0, 0);
    }
}

/*
解題思路（Sudoku Solver，回溯 + 狀態記錄）：
1) 狀態結構：
   - rows[i][d]：第 i 列是否已有數字 d（1..9）
   - columns[j][d]：第 j 欄是否已有數字 d
   - boxes[b][d]：第 b 個 3x3 宮是否已有數字 d，b = (i/3)*3 + (j/3)
   - 以上用計數（0/1）表示可否放置，查詢 O(1)。

2) 放置與撤回：
   - couldPlace(d, r, c)：檢查 d 在 (r,c) 所在列/欄/宮是否未出現。
   - placeNumber / removeNumber：同步更新 rows/columns/boxes 與棋盤 board。

3) 遍歷順序：
   - 從左到右、由上到下依序填格（backtrack(r,c)）。
   - 若遇到 '.' 則嘗試放 1..9；若是數字，直接遞進下一格（placeNextNumbers）。

4) 終止條件：
   - 到達最後一格（row == 8 且 col == 8）後，成功填入即標記 sudokuSolved = true，並停止回溯。

5) 初始化：
   - solveSudoku：先掃描盤面，對既有數字呼叫 placeNumber 建立初始狀態，然後從 (0,0) 開始回溯。

複雜度：
   - 最壞情況指數級，但透過 rows/cols/boxes 的 O(1) 檢查與剪枝大幅加速；空間 O(1)（固定 9x10 陣列）。
 */
