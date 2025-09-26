class Solution {
    String solution(int k) {
        // S = (2^k - 1) * (2^k) / 2
        //   = (2^k - 1) * 2^(k-1)
        //   = (2^k - 1) << (k-1)
        var sb = new StringBuilder();
        for (int i = 0; i < k; ++i) sb.append('1');
        for (int i = 0; i < k - 1; ++i) sb.append('0');
        return sb.toString();
    }
}