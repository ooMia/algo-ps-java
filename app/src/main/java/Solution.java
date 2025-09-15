class Solution {
    // 45656이란 수를 보자.
    // 이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.
    // N이 주어질 때, 길이가 N이면서 0부터 9까지 숫자가 모두 등장하는 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오.
    // 0으로 시작하는 수는 계단수가 아니다.

    // 숫자 K로 끝나면서 길이가 N이고, mask가 M인 계단 수의 개수
    final long[][][] dp = new long[10][100 + 1][1 << 10];
    final long mod = 1_000_000_000;
    final int FULL_MASK = (1 << 10) - 1;

    Solution() {
        for (int i = 1; i <= 9; ++i) {
            dp[i][1][1 << i] = 1;
        }
    }

    long solution(int N) {
        if (N < 10)
            return 0;

        for (int n = 2; n <= N; ++n) {// 길이
            for (int k = 0; k <= 9; ++k) { // 끝나는 숫자
                for (int m = 0; m <= FULL_MASK; ++m) {
                    // 0보다 큰 수로 끝난다면 이전 끝 수는 k-1 가능
                    int newMask = m | (1 << k);
                    if (k > 0) {
                        int prev = k - 1;
                        dp[k][n][newMask] += dp[prev][n - 1][m];
                    }
                    // 9보다 작은 수로 끝난다면 이전 끝 수는 k+1 가능
                    if (k < 9) {
                        int prev = k + 1;
                        dp[k][n][newMask] += dp[prev][n - 1][m];
                    }
                    dp[k][n][newMask] %= mod;
                }
            }
        }

        long ans = 0;
        for (int n = 0, mask = FULL_MASK; n <= 9; ++n) {
            ans = (ans + dp[n][N][mask]) % mod;
        }
        return ans;
    }
}