import java.util.Arrays;

class Solution {

    final int N; // 수열의 길이
    final long[] numbers;

    final long[] res = new long[3]; // { absSum, iLeft, iRight }
    final long[] cumuls;

    Solution(int N, int[] numbers) {
        this.N = N;
        this.numbers = Arrays.stream(numbers).asLongStream().toArray();
        this.cumuls = new long[N + 1];
    }

    public long[] solve() {
        step1();
        System.err.println("step1: " + Arrays.toString(res));
        if (res[0] != 0) {
            step2();
            System.err.println("step2: " + Arrays.toString(res));
        }
        return new long[] { res[0], res[1] + 1, res[2] + 1 };
    }

    void step1() {
        // 누적합 배열 생성 (cumuls[0]=0, cumuls[i]=cumuls[i-1]+numbers[i-1])
        cumuls[0] = 0;
        res[0] = Math.abs(numbers[0]);
        res[1] = res[2] = 0;
        for (int i = 1; i <= N; ++i) {
            cumuls[i] = cumuls[i - 1] + numbers[i - 1];
            long num = numbers[i - 1];
            if (Math.abs(num) < Math.abs(res[0])) {
                res[0] = num;
                res[1] = i - 1;
                res[2] = i - 1;
                if (num == 0)
                    return;
            }
        }
        // [0, k] 구간합도 검사
        for (int i = 1; i <= N; ++i) {
            long sum = cumuls[i];
            if (Math.abs(sum) < Math.abs(res[0])) {
                res[0] = sum;
                res[1] = 0;
                res[2] = i - 1;
                if (sum == 0)
                    return;
            }
        }
    }

    void step2() {
        // 누적합 배열을 값 기준으로 정렬하여 인접한 값의 차이가 최소가 되는 구간을 찾음
        Pair[] arr = new Pair[N + 1];
        for (int i = 0; i <= N; ++i) {
            arr[i] = new Pair(cumuls[i], i);
        }
        Arrays.sort(arr, (a, b) -> Long.compare(a.value, b.value));
        for (int i = 1; i <= N; ++i) {
            long diff = arr[i].value - arr[i - 1].value;
            if (Math.abs(diff) < Math.abs(res[0])) {
                res[0] = diff;
                int l = Math.min(arr[i].index, arr[i - 1].index);
                int r = Math.max(arr[i].index, arr[i - 1].index) - 1;
                res[1] = l;
                res[2] = r;
                if (diff == 0)
                    return;
            }
        }
    }

    static class Pair {
        long value;
        int index;
        Pair(long value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}