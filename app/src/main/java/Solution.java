class Solution {
    int[][][] dp = new int[21][21][21];

    int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0)
            return 1;
        if (a > 20 || b > 20 || c > 20)
            return w(20, 20, 20);
        if (dp[a][b][c] != 0)
            return dp[a][b][c];
        if (a < b && b < c)
            return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }

    String solution(int a, int b, int c) {
        return format(a, b, c, w(a, b, c));
    }

    String format(int a, int b, int c, int res) {
        return String.format("w(%d, %d, %d) = %d\n", a, b, c, res);
    }
}