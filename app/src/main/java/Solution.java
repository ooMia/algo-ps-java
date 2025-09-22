import java.util.BitSet;

class Solution {
    int solution(int nRows, int nCols, boolean[][] board) {
        BitSet rows = new BitSet(nRows);
        BitSet cols = new BitSet(nCols);
        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < nCols; ++j) {
                if (board[i][j]) {
                    rows.set(i);
                    cols.set(j);
                }
            }
        }
        return Math.max(nRows - rows.cardinality(), nCols - cols.cardinality());
    }
}