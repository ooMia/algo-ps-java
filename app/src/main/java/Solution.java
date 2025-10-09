class Solution {
    final int MOD = 1_000_000_007;
    final int[] dp = new int[191_229 + 1];

    Solution() {
        dp[1] =1;
        dp[2] =2;
        for (int i = 3; i <= 191_229; ++i) {
            long temp = (long) dp[i - 1] + dp[i - 2];
            dp[i] = (int) (temp % MOD);
        }
    }

    public String solution(int[] ns) {
        var sb = new StringBuilder();
        for (var n : ns) {
            sb.append(dp[n]).append('\n');
        }
        return sb.toString();
    }
}