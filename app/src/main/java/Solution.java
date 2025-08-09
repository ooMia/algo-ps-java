class Solution {

    final int[] numbers;

    Solution(int[] numbers) {
        this.numbers = numbers;
    }

    public int solution() {
        int N = numbers.length;
        int[] cumuls = new int[N + 1]; // sigma [0, k)
        for (int i = 1; i <= N; i++) {
            cumuls[i] = cumuls[i - 1] + numbers[i - 1];
        }

        int res = cumuls[0];
        for (int i = N; i > 0; --i) {
            int sum = cumuls[i];
            if (sum == 100)
                return sum;

            int abs = Math.abs(100 - sum);
            int resAbs = Math.abs(100 - res);
            if (abs == resAbs)
                res = Math.max(res, sum);
            else if (abs < resAbs)
                res = sum;
        }

        return res;
    }
}