class Solution {
    final int n;
    final int[] dp;

    Solution(int n) {
        this.n = n;
        this.dp = new int[n + 1];
    }

    int solution() {
        for (int i = 1; i <= n; ++i) {
            int ans = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; ++j) {
                ans = Math.min(ans, dp[i - j * j] + 1);
            }
            dp[i] = ans;
        }
        return dp[n];
    }
}