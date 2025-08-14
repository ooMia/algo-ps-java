import java.math.BigInteger;

class Solution {

    final int n;
    final BigInteger mod = BigInteger.valueOf(1_000_000_007);
    final BigInteger[] fibo;

    Solution(int n) {
        this.n = n;
        this.fibo = new BigInteger[n + 2];
        fibo[0] = BigInteger.ZERO;
        fibo[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            fibo[i] = fibo[i - 1].add(fibo[i - 2]).mod(mod);
        }
    }

    public BigInteger solution() {
        return fibo[n];
    }
}