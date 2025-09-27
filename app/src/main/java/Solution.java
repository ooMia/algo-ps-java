import java.util.Arrays;

class Solution {
    private final double[] probs;
    private final int[][] choices;
    private final int N;

    Solution(int N) {
        this.N = N;
        int size = Math.max(4, N + 1);
        probs = new double[size];
        choices = new int[size][];

        probs[0] = 1.0;
        probs[1] = probability(1, 1);
        probs[2] = probability(2, 2);
        probs[3] = probability(3, 3);

        choices[0] = new int[]{};
        choices[1] = new int[]{1};
        choices[2] = new int[]{2};
        choices[3] = new int[]{3};

        for (int target = 1; target <= N; ++target) {
            for (int k = 1; k <= target; ++k) {
                double trial = probs[target - k] * probability(target, k);
                if (trial > probs[target]) {
                    probs[target] = trial;
                    int newSize = choices[target - k].length + 1;
                    choices[target] = new int[newSize];
                    System.arraycopy(choices[target - k], 0, choices[target], 0, newSize - 1);
                    choices[target][newSize - 1] = k;
                }
            }
        }
    }

    private static double probability(int n, int k) {
        return 1.0 - (double) k / (n + 1);
    }

    String solution() {
        System.err.println(Arrays.toString(probs));
        int X = choices[N].length;
        var history = choices[N];

        var sb = new StringBuilder();
        sb.append(X).append('\n');
        for (int hist : history) {
            sb.append(hist).append(' ');
        }
        return sb.toString().trim();
    }
}