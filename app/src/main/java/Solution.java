class Solution {

    private final int N, M;

    Solution(int N, int M) {
        this.N = N;
        this.M = M;
    }

    public String solution() {
        return 100 * N >= M ? "Yes" : "No";
    }

}
