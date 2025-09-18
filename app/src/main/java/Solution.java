class Solution {
    final int n;
    final int[] dp;

    Solution(int n) {
        this.n = n;
        this.dp = new int[n + 1];
    }

    int solution() {
        // dp[i] = i를 제곱수의 합으로 나타낼 때, 필요한 최소 항의 개수
        // [1, n] 구간의 모든 자연수 i와 i 이하의 제곱수 j*j에 대해 dp[i - j*j] + 1의 최솟값으로 갱신
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