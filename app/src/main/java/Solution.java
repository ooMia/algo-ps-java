class Solution {
    final int N;
    final long[][] points;

    public Solution(int N, long[][] points) {
        this.N = N;
        this.points = points;
    }

    public String solution() {
        // use Shoelace Formula
        long sum = 0;
        for (int i = 0; i < N; ++i) {
            var n1 = points[i][0] * points[i + 1][1];
            var n2 = points[i][1] * points[i + 1][0];
            sum += n1 - n2;
        }
        return String.format("%.1f", Math.abs(sum) / 2.0);
    }
}
