class Solution {
    String solution(int N) {
        var res = new int[N];
        res[0] = N;

        int cur = -N + 1;
        for (int i = 1; i < N; ++i) {
            res[i] = res[i - 1] + cur;
            int sign = cur > 0 ? +1 : -1;
            cur = -sign * (Math.abs(cur) - 1);
        }

        var sb = new StringBuilder();
        sb.ensureCapacity(N * 2);
        for (int i = 0; i < N; ++i) {
            sb.append(res[i]).append(' ');
        }
        return sb.toString();
    }
}