class Solution {
    final long[] dp = new long[101];

    Solution() {
        // 0, 1, 1, 1, 2, 2, 3, 4, 5, 7, 9
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        dp[6] = 3;
        dp[7] = 4;
        dp[8] = 5;
        dp[9] = 7;
        dp[10] = 9;
        for (int i = 11; i <= 100; ++i) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }
    }

    public long solution(int N) {
        return dp[N];
    }
}