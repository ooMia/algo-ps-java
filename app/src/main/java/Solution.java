class Solution {
    final int N, P;

    Solution(int N, int P) {
        this.N = N;
        this.P = P;
    }

    public int solution() {
        if (P - N < N) {
            // 윌슨의 정리 활용한 모듈러-팩토리얼 구하기
            // 0 <= n < P, P는 소수
            return factorialModP_Wilson(N, P);
        } else {
            // 기본 팩토리얼 방식 사용
            return basicFactorial(N);
        }
    }

    int basicFactorial(int n) {
        int res = 1;
        for (int i = 1; i <= N; i++) {
            res = modMul(res, i, P);
        }
        return res;
    }

    // (p-1)! = n! * (n+1) * ... * (p-1) ≡ -1 (mod p)
    // n! = (p-1)! / ((n+1) * ... * (p-1)) % P
    //
    // - prod: (n+1) * (n+2) * ... * (P-1) % P
    // - inv: prod^(-1) % P (페르마의 소정리)
    // - res: -1 * inv % P (음수 처리)
    int factorialModP_Wilson(int n, int p) {
        int prod = 1;
        for (int i = n + 1; i < p; i++) {
            prod = modMul(prod, i, p);
        }
        int inv = modInverse(prod, p);
        // -1 ≡ p - 1 (mod p)
        // (-1 * inv) % p ≡ ((p - 1) * inv) % p
        int res = modMul(p - 1, inv, p);
        return res;
    }

    // 모듈러 곱셈: (a * b) % m
    int modMul(int a, int b, int m) {
        long res = a * b;
        return (int) (res % m);
    }

    // 모듈러 역원: a^(-1) % m (m은 소수, 페르마의 소정리)
    // a^(m-1) ≡ 1 (mod m)
    // a^(m-2) ≡ a^(-1) (mod m)
    int modInverse(int a, int m) {
        return powMod(a, m - 2, m);
    }

    // 거듭제곱 모듈러: a^b % m
    int powMod(int a, int b, int m) {
        int result = 1;
        a = a % m;
        while (b > 0) {
            if ((b & 1) == 1) { // b % 2 == 1
                result = modMul(result, a, m);
            }
            a = modMul(a, a, m);
            b >>= 1; // b /= 2
        }
        return result;
    }
}