import java.math.BigInteger;

class Solution {
    public String solution(long[][] circles) {
        var p0 = circles[0];
        var p1 = circles[1];
        BigInteger dx = BigInteger.valueOf(p1[0]).subtract(BigInteger.valueOf(p0[0]));
        BigInteger dy = BigInteger.valueOf(p1[1]).subtract(BigInteger.valueOf(p0[1]));
        BigInteger distSq = dx.multiply(dx).add(dy.multiply(dy));
        BigInteger rSum = BigInteger.valueOf(p0[2]).add(BigInteger.valueOf(p1[2]));
        BigInteger rSumSq = rSum.multiply(rSum);
        return (distSq.compareTo(rSumSq) < 0) ? "YES" : "NO";
    }
}
