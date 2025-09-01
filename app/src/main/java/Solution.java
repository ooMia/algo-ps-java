class Solution {
    public String solution(int N, int[][] grid) {

        // 숫자 2가 어떤 대각선 위에 놓였는지 확인한다.
        int k = -1;
        for (int r = 0; r < N && k == -1; ++r) {
            for (int c = 0; c < N; ++c) {
                if (grid[r][c] == 2) {
                    k = (r + c) & 1;
                    break;
                }
            }
        }

        // 서로 같은 대각선 위에 '1'이 놓여있는지 확인한다.
        for (int r = 0; r < N; ++r)
            for (int c = k ^ (r & 1); c < N; c += 2)
                if (grid[r][c] == 1)
                    return "Kiriya";

        return "Lena";
    }
}