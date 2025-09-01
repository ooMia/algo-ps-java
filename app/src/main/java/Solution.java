import java.math.BigInteger;

class Solution {
    final int N;
    final String line;

    Solution(int N, String line) {
        this.N = N;
        this.line = line;
    }

    public BigInteger solution() {
        BigInteger res = BigInteger.ZERO;
        int iStart = 0;
        while (iStart < N) {
            // find first '2'
            while (iStart < N && line.charAt(iStart) != '2')
                ++iStart;
            // find local last '2'
            int iEnd = iStart;
            while (iEnd < N && line.charAt(iEnd) == '2')
                ++iEnd;
            res = res.add(score(iStart, iEnd));
            iStart = iEnd + 1;
        }
        return res;
    }

    BigInteger score(int iStart, int iEnd) {
        // [iStart, iEnd)
        BigInteger res = BigInteger.ZERO;
        int len = iEnd - iStart;
        for (int score = 1; len > 0; ++score, --len) {
            var tmp = BigInteger.valueOf(len).multiply(BigInteger.valueOf(score));
            res = res.add(tmp);
        }
        return res;
    }
}