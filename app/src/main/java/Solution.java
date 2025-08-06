class Solution {

    public String solution(int N) {
        StringBuilder sb = new StringBuilder();
        int row = 1;

        while (row <= N) {
            for (int b = 0; b < N - row; ++b) {
                sb.append(" ");
            }
            for (int s = 0; s < row * 2 - 1; ++s) {
                sb.append("*");
            }
            sb.append("\n");
            ++row;
        }
        return sb.toString();
    }
}