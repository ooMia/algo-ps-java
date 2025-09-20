class Solution {
    int solution(int n, int m) {
        // (0, n) 구간 내 a < b를 만족하는 정수 쌍 (a, b)에 대해
        // (a^2 + b^2 + m) % (a * b) == 0 인 쌍의 개수

        int res = 0;
        for (int a = 1; a < n; ++a) {
            for (int b = a + 1; b < n; ++b) {
                if (isValid(a, b, m)) {
                    res++;
                }
            }
        }
        return res;
    }

    boolean isValid(int a, int b, int m) {
        return (a * a + b * b + m) % (a * b) == 0;
    }
}