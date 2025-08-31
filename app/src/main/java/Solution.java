class Solution {
    final int N;
    final int[] prices;

    Solution(int N, int[] prices) {
        this.N = N;
        this.prices = prices;
    }

    public int solution() {
        int[] minValue = new int[N];
        int[] maxValue = new int[N];

        minValue[0] = prices[0];
        for (int i = 1; i < N; ++i)
            minValue[i] = Math.min(minValue[i - 1], prices[i]);

        maxValue[N - 1] = prices[N - 1];
        for (int i = N - 2; i >= 0; --i)
            maxValue[i] = Math.max(maxValue[i + 1], prices[i]);

        int maxProfit = 0;
        for (int i = 0; i < N; ++i) {
            maxProfit = Math.max(maxProfit, maxValue[i] - minValue[i]);
        }
        return maxProfit;
    }
}