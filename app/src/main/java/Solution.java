class Solution {

    final int nRows, nCols;
    final int[][] cumuls;

    Solution(int nRows, int nCols, int[][] numbers) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.cumuls = new int[nRows + 1][nCols + 1];

        for (int r = 1; r <= nRows; ++r) {
            for (int c = 1; c <= nCols; ++c) {
                cumuls[r][c] = cumuls[r - 1][c] + cumuls[r][c - 1] + numbers[r - 1][c - 1] - cumuls[r - 1][c - 1];
            }
        }
    }

    public int solution(int y1, int x1, int y2, int x2) {
        // sD(y2, x2) - sC(y1-1, x2) - sB(y2, x1-1) + sA(y1-1, x1-1)
        return cumuls[y2][x2] - cumuls[y1 - 1][x2] - cumuls[y2][x1 - 1] + cumuls[y1 - 1][x1 - 1];
    }
}