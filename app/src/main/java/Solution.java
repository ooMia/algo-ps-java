import java.util.HashSet;
import java.util.Set;

class Solution {

    final int N; // 정수의 개수
    final int[] numbers;

    Solution(int N, int[] numbers) {
        this.N = N;
        this.numbers = numbers;
    }

    public long solve() {
        var sets = sumSubset(numbers);
        for (int n=1; ;++n) {
            if (!sets.contains(n)) {
                return n;
            }
        }
    }

    Set<Integer> sumSubset(int[] weights) {
        int n = weights.length;
        Set<Integer> sums = new HashSet<>(1 << n);

        for (int mask = 0; mask <= (1 << n) - 1; ++mask) {
            int sum = sum(weights, mask);
            sums.add(sum);
        }
        return sums;
    }

    int sum(int[] arr, int mask) {
        int sum = 0;
        for (int i = 0; i < arr.length; ++i) {
            if ((mask & (1 << i)) != 0) {
                sum += arr[i];
            }
        }
        return sum;
    }

}