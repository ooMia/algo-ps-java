class Solution {

    final int MOD = 10007;
    final int nCols;
    final int[] dp;

    Solution(int nCols) {
        this.nCols = nCols;
        this.dp = new int[nCols + 3];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
    }

    public int solution() {
        for (int i = 3; i <= nCols; ++i) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }
        return dp[nCols];
    }
}