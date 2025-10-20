import java.util.Arrays;

class Solution {
    public int solution(int nRows, int nCols, int[][] positions) {
        final int iDestRow = 1, iDestCol = nCols - 1;
        int winnerId = 0, winnerDist = Integer.MAX_VALUE;
        for (int i = 0; i < positions.length; ++i) {
            var pos = positions[i];
            int dist = manhattan(pos[0], pos[1], iDestRow, iDestCol);
            if (dist < winnerDist) {
                winnerDist = dist;
                winnerId = i + 1;
            }
        }
        return winnerId;
    }

    private int manhattan(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
