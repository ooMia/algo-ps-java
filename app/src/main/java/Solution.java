class Solution {
    final int modifier = 15746;
    final int N;
    final int[] dp;

    Solution(int N) {
        this.N = N;
        dp = new int[Math.max(5, N+1)];
        dp[1] = 1; // 1
        dp[2] = 2; // 11, 00
        dp[3] = 3; // 1[11, 00], 00[1]
        dp[4] = 5; // 1(dp[3]) 00(dp[2])
    }

    public int solution() {
        for (int i = 5; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % modifier;
        }
        return dp[N];
    }
}