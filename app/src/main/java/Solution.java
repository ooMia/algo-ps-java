import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    final int N; // 전체 아이템 개수
    final int C; // 최대 소지 무게
    final int[] weights;

    Solution(int N, int C, int[] weights) {
        this.N = N;
        this.C = C;

        List<Integer> _weights = new ArrayList<>(N);
        for (int weight : weights) {
            if (weight <= C) { // 최대 소지 무게보다 작거나 같은 아이템만 고려
                _weights.add(weight);
            }
        }
        this.weights = _weights.stream().mapToInt(i -> i).toArray();
    }

    public int solve() {
        int n = weights.length;
        if (n <= 1) {
            return n + 1; // 공집합 포함
        }
        var sub1 = sumSubset(Arrays.copyOfRange(weights, 0, n / 2));
        var sub2 = sumSubset(Arrays.copyOfRange(weights, n / 2, n));

        Arrays.sort(sub2); // 이진 탐색을 위해 정렬

        int count = 0;
        for (var s1 : sub1) {
            if (s1 > C)
                continue;

            int l = 0, r = sub2.length - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (sub2[mid] + s1 > C) {
                    r = mid - 1; // 합이 최대 소지 무게를 초과
                } else {
                    l = mid + 1; // 합이 최대 소지 무게 이하
                }
            }
            count += r + 1; // sub2에서 s1과 합이 최대 소지 무게 이하인 경우의 수
        }

        if (sub1.length < 10 && sub2.length < 10) {
            System.err.println("left: " + Arrays.toString(sub1));
            System.err.println("right: " + Arrays.toString(sub2));
        }
        return count;
    }

    long[] sumSubset(int[] weights) {
        int n = weights.length;
        List<Long> sums = new ArrayList<>(1 << n);

        for (int mask = 0; mask <= (1 << n) - 1; ++mask) {
            long sum = sum(weights, mask);
            if (sum <= C) { // 최대 소지 무게보다 작거나 같은 경우
                sums.add(sum);
            }
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