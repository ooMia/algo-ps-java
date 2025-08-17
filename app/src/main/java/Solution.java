import java.util.Arrays;

class Solution {
    final int C; // C: promotion goal
    final int N; // N: number of promotions
    final int[][] promotions; // [0]: cost, [1]: benefit
    final int maxBenefit;

    public Solution(int C, int N, int[][] promotions) {
        this.C = C;
        this.N = N;
        this.promotions = promotions;
        int benefit = Integer.MIN_VALUE;
        for (var p : promotions) {
            benefit = Math.max(benefit, p[1]);
        }
        this.maxBenefit = benefit;
    }

    public int solution() {
        int limit = C + maxBenefit;
        int[] dp = new int[limit + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (var p : promotions) {
            System.err.println(Arrays.toString(p));
            var cost = p[0];
            var benefit = p[1];
            for (int i = benefit; i <= limit; ++i) {
                if (dp[i - benefit] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - benefit] + cost);
                }
            }
        }

        // [C, limit] 구간 내 최소 비용 반환
        int res = Integer.MAX_VALUE;
        for (int i = C; i <= limit; ++i) {
            res = Math.min(res, dp[i]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
