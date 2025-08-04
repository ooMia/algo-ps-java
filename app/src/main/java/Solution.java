import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    final int N; // 정수의 개수
    final int S; // 목표 합
    final int[] numbers;

    Solution(int N, int S, int[] numbers) {
        this.N = N;
        this.S = S;
        this.numbers = numbers;
    }

    public long solve() {
        int n = numbers.length;
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return numbers[0] == S ? 1 : 0;
        }

        var sub1 = sumSubset(Arrays.copyOfRange(numbers, 0, n / 2));
        var _sub2 = sumSubset(Arrays.copyOfRange(numbers, n / 2, n));

        Map<Long, Integer> sub2 = new HashMap<>();
        for (long s2 : _sub2) {
            sub2.put(s2, sub2.getOrDefault(s2, 0) + 1);
        }

        long count = 0;
        System.err.println(Arrays.toString(sub1));
        System.err.println(Arrays.toString(_sub2));
        System.err.println(sub2.toString());
        for (var s1 : sub1) {
            count += sub2.getOrDefault(S - s1, 0);
            System.err.println(count);
        }

        if (S == 0) {
            count -= 1;
        }

        return count;
    }

    long[] sumSubset(int[] weights) {
        int n = weights.length;
        List<Long> sums = new ArrayList<>(1 << n);

        for (int mask = 0; mask <= (1 << n) - 1; ++mask) {
            long sum = sum(weights, mask);
            sums.add(sum);
        }
        return sums.stream().mapToLong(i -> i).toArray();
    }

    long sum(int[] arr, int mask) {
        long sum = 0;
        for (int i = 0; i < arr.length; ++i) {
            if ((mask & (1 << i)) != 0) {
                sum += arr[i];
            }
        }
        return sum;
    }

}