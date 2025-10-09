class Solution {
    final int MOD = 1_000_000_007;
    final int[] dp = new int[191_229 + 1];
    int iLast = 2;

    Solution() {
        dp[1] = 1;
        dp[2] = 2;
    }

    public String solution(int[] ns) {
        var sb = new StringBuilder();
        for (var n : ns) {
            while (iLast < n) {
                ++iLast;
                long temp = (long) dp[iLast - 1] + dp[iLast - 2];
                dp[iLast] = (int) (temp % MOD);
            }
            sb.append(dp[n]).append('\n');
        }
        return sb.toString();
    }
}