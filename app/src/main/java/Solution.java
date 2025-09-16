class Solution {
    // 두 자연수 A, B가 주어졌을 때,
    // A ≤ x ≤ B를 만족하는 모든 x에 대해 x를 이진수로 표현했을 때
    // 1의 개수의 합을 구하는 프로그램을 작성하시오.

    // 2^x <= k < 2^(x+1)를 만족하는 모든 k를 이진수로 표현했을 때 1의 개수의 합
    long dp[] = new long[54 + 1]; // log_2(10^16) < 54

    Solution() {
        // 주어진 비트가 1개면 dp[0] = 1
        // 주어진 비트가 2개면 dp[1] = 2^1 + 2 * dp[0]
        // 주어진 비트가 3개면 dp[2] = 2^2 + 2 * dp[1]
        dp[0] = 1;
        for (int bit = 1; bit <= 54; ++bit) {
            dp[bit] = (1L << bit) + 2 * dp[bit - 1];
        }
    }

    /**
     * A부터 B까지의 모든 수에 대해 이진수 표현 시 1의 개수 총합을 구합니다.
     * 
     * @param A 시작 자연수
     * @param B 끝 자연수
     * @return 1의 개수 총합
     */
    long solution(long A, long B) {
        // [0, B] - [0, A-1] = [A, B]
        return countSetBits(B) - countSetBits(A - 1);
    }

    /**
     * 0부터 n까지의 모든 수에 대해 이진수 표현 시 1의 개수 총합을 구합니다.
     * 
     * @param n 끝 자연수
     * @return 1의 개수 총합
     */
    long countSetBits(long n) {
        if (n <= 0) {
            return 0;
        }

        // n의 최상위 비트(MSB) 위치를 찾습니다. (0-indexed)
        int msbPos = getMsbPosition(n);

        // 1. [0, 2^msbPos - 1] 구간의 1의 개수
        // dp 배열의 정의에 따라 dp[msbPos-1] 입니다.
        long count = (msbPos > 0) ? dp[msbPos - 1] : 0;

        // 2. [2^msbPos, n] 구간에서 MSB를 포함한 1의 개수를 반영합니다.
        // 구간의 숫자 개수는 (n - 2^msbPos + 1)입니다.
        long remainder = n - (1L << msbPos);
        count += (remainder + 1);

        // 3. [2^msbPos, n] 구간에서 MSB를 제외한 나머지 비트들의 1의 개수
        // 이는 [0, remainder] 구간의 1의 개수와 같습니다.
        count += countSetBits(remainder);

        return count;
    }

    /**
     * n의 최상위 비트(MSB) 위치를 찾습니다. (0-indexed)
     * 예를 들어 n=13 (1101)이면, msbPos = 3
     * 
     * @param n 자연수
     * @return 최상위 비트 위치
     */
    int getMsbPosition(long n) {
        int pos = 0;
        while ((1L << (pos + 1)) <= n) {
            pos++;
        }
        return pos;
    }
}