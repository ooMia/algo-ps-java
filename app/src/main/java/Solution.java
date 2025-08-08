class Solution {

    final int N; // N: nRows
    StringBuilder sb = new StringBuilder();

    Solution(int nRows) {
        this.N = nRows;
    }

    public String solution() {
        append(1);
        return sb.toString();
    }

    void append(int row) {
        if (row == N) {
            // 2 * N - 1 stars
            for (int j = 0; j < 2 * N; ++j) {
                sb.append("*");
            }
            sb.append("\n");
            return;
        }

        StringBuilder _sb = new StringBuilder();
        for (int j = 0; j < row; ++j) {
            _sb.append("*");
        }
        for (int j = 0; j < 2 * (N - row); ++j) {
            _sb.append(" ");
        }
        for (int j = 0; j < row; ++j) {
            _sb.append("*");
        }
        sb.append(_sb.toString()).append("\n");
        append(row + 1);
        sb.append(_sb.toString()).append("\n");
    }
}