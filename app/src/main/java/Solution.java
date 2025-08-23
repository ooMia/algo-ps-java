import java.math.BigInteger;

class Solution {

    public String solution(String N, String M) {
        BigInteger n = new BigInteger(N);
        BigInteger m = new BigInteger(M);

        var sb = new StringBuilder();
        sb.append(n.divide(m)).append('\n');
        sb.append(n.remainder(m));
        return sb.toString();
    }
}
