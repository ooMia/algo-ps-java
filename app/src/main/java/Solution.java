class Solution {

    final int N, S; // N: 수열의 길이, S: 부분 합 하한
    final int[] numbers;

    Solution(int N, int S, int[] numbers) {
        this.N = N;
        this.S = S;
        this.numbers = numbers;
    }

    public int solve() {
        int[] cumuls = new int[N + 1];
        for (int i = 0; i < N; ++i) {
            cumuls[i + 1] = cumuls[i] + numbers[i];
        }

        if (cumuls[N] < S) {
            return 0; // 부분 합 하한을 만족하는 수열이 없음
        }

        int iSub = 0, iAdd = 0, res = Integer.MAX_VALUE;
        while (true) {
            int sum = cumuls[iAdd] - cumuls[iSub];
            // System.err.println("iSub: " + iSub + ", iAdd: " + iAdd + ", sum: " + sum);
            if (sum < S) {
                ++iAdd;
                if (iAdd > N) {
                    return res == Integer.MAX_VALUE ? 0 : res;
                }
            }
            else if (sum >= S) {
                res = Math.min(res, iAdd - iSub);
                ++iSub;
                if (iSub > N) {
                    return res;
                }
            }
        }
    }

}