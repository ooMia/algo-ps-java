import java.util.HashMap;
import java.util.Map;

class Solution {

    final int n;
    final long mod = 1_000_000_007;
    Map<Integer, Long> fibo;

    Solution(long _n) {
        final long cycle = 2_000_000_016; // pre-calculated cycle length for given mod
        this.n = (int) (_n % cycle);
        this.fibo = new HashMap<>();
        this.fibo.put(0, 0L);
        this.fibo.put(1, 1L);
        this.fibo.put(2, 1L);
        this.fibo.put(3, 2L);
    }

    public long solution() {
        if (n <= 1)
            return n;
        return fibo(n);
    }

    long fibo(int k) {
        if (fibo.containsKey(k)) {
            return fibo.get(k);
        }
        // use d'Ocagne's identity
        // F(m+n) = F(m-1)*F(n) + F(m)*F(n+1)
        int n = k / 2, m = k - n;
        long P1 = (fibo(m - 1) * fibo(n)) % mod;
        long P2 = (fibo(m) * fibo(n + 1)) % mod;
        long res = (P1 + P2) % mod;
        fibo.put(k, res);
        return res;
    }

}