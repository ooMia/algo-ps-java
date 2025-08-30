class Solution {
    final int N;
    final int[] numbers;

    public Solution(int N, int[] number) {
        this.N = N;
        this.numbers = number;
    }

    public int solution() {
        int[] dp = new int[N]; // 자신이 마지막 원소인 부분 수열의 최대 합
        for (int i = 0; i < N; ++i)
            dp[i] = numbers[i];

        // DP 계산
        for (int i = 1; i < N; ++i) {
            int curNum = numbers[i];
            for (int j = 0; j < i; ++j) {
                int prevNum = numbers[j];
                if (prevNum < curNum) {
                    dp[i] = Math.max(dp[i], dp[j] + curNum);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}