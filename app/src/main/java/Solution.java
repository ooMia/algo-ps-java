import java.math.BigInteger;

class Solution {
    public BigInteger solution(int N, int M) {
        M = Math.min(M, N - M); // nCm == nC(n-m)

        BigInteger numerator = BigInteger.ONE;
        BigInteger _N = BigInteger.valueOf(N);
        for (BigInteger n=BigInteger.valueOf(N-M+1); n.compareTo(_N) <= 0; n = n.add(BigInteger.ONE)) {
            numerator = numerator.multiply(n);
        }

        BigInteger denominator = BigInteger.ONE;
        BigInteger _M = BigInteger.valueOf(M);
        for (BigInteger m = BigInteger.ONE; m.compareTo(_M) <= 0; m = m.add(BigInteger.ONE)) {
            denominator = denominator.multiply(m);
        }

        return numerator.divide(denominator);
    }
}