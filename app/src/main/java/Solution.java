class Solution {
    // 숫자 n으로 시작하고, 길이가 m인 자연수 중 계단 수에 해당하는 수의 개수
    long[][] dp = new long[10][100 + 1];

    int mod = 1_000_000_000;

    Solution() {
        // 한 자리수는 모두 계단 수
        for (int i = 1; i <= 9; i++) {
            dp[i][1] = 1;
        }
    }

    long solution(int N) {
        if (N == 1)
            return 9;

        for (int len = 2; len <= N; ++len) {
            for (int num = 0; num <= 9; ++num) {
                if (num == 0) {
                    dp[num][len] = dp[num + 1][len - 1] % mod;
                } else if (num == 9) {
                    dp[num][len] = dp[num - 1][len - 1] % mod;
                } else {
                    dp[num][len] = (dp[num - 1][len - 1] + dp[num + 1][len - 1]) % mod;
                }
            }
        }

        long ans = 0;
        for (int i = 0; i <= 9; ++i)
            ans = (ans + dp[i][N]) % mod;
        return ans;
    }
}