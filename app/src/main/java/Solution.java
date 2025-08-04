class Solution {

    final int N;

    Solution(int N) {
        this.N = N;
    }

    public int solve() {
        for (int i = 3; i < 10101; ++i) {
            if (1 + i + i * i == N) {
                return i;
            }
        }
        return -1;
    }
}