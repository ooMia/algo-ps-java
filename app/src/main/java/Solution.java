import java.util.Arrays;

class Solution {
    final int N, M;
    final int[] woods;
    final long[] cumuls;

    Solution(int N, int M, int[] woods) {
        this.N = N;
        this.M = M;
        this.woods = woods;
        Arrays.sort(woods);
        this.cumuls = new long[N + 1];// sum [0, i)
        for (int i = 1; i <= N; ++i) {
            cumuls[i] = cumuls[i - 1] + woods[i - 1];
        }
    }

    public long solution() {
        int low = 0, high = woods[N - 1] - 1;
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (cut(mid) >= M) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    long cut(int height) {
        // 1. 오름차순으로 정렬된 woods에 대해
        // 2. height보다 크거나 같은 나무의 최소 인덱스 iWood를 찾고
        int iWood = Arrays.binarySearch(woods, height);
        if (iWood < 0) {
            iWood = -iWood - 1;
        }

        // 3. 그 나무를 기준으로 C - B - A를 계산한다.
        // C: 전체 나무의 높이 합 = cumuls[N]
        // B: height보다 크거나 같은 나무들이 cut 이후 남게되는 높이의 합 = (N - iWood) * height
        // A: height보다 작은 나무들의 높이 합 = cumuls[iWood]
        var C = cumuls[N];
        var B = (N - iWood) * (long) height;
        var A = cumuls[iWood];
        return C - B - A;
    }

}