class Solution {

    final int N;
    final int K;
    final int[] temperatures;

    Solution(int nCols, int k, int[] temperatures) {
        this.N = nCols;
        this.K = k;
        this.temperatures = temperatures;
    }

    public int solution() {
        int[] cumuls = new int[N + 1]; // sum arr [0, i)
        for (int i = 1; i <= N; ++i) {
            cumuls[i] = cumuls[i - 1] + temperatures[i - 1];
        }

        int maxSum = cumuls[K];
        for (int i = K; i <= N; ++i) {
            int sum = cumuls[i] - cumuls[i - K];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
}