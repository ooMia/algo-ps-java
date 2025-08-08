class Solution {

    final int baseTime = 100;
    final int baseFee = 10;
    final int unitTime = 50;
    final int unitFee = 3;

    public String solution(int N) {
        StringBuilder sb = new StringBuilder();

        for (int row = 1; row <= N; ++row) {
            // 1. i blanks
            for (int j = 0; j < row - 1; ++j) {
                sb.append(" ");
            }
            // 2. 2(N-row) + 1 stars
            for (int j = 0; j < 2 * (N - row) + 1; ++j) {
                sb.append("*");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}