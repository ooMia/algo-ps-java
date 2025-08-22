class Solution {
    final int N;
    final String ioi;

    final StringBuilder sb = new StringBuilder();
    int count = 0;

    Solution(int N, String ioi) {
        this.N = N;
        this.ioi = ioi;
    }

    public int solution() {
        for (char c : ioi.toCharArray()) {
            append(c);
        }
        flush();
        return count;
    }

    void append(char c) {
        var n = sb.length();
        if ((n == 0 && c == 'I') || (n > 0 && sb.charAt(n - 1) != c))
            sb.append(c);
        else {
            flush();
            if (c == 'I')
                sb.append(c);
        }
        System.err.println(sb.toString());
    }

    void flush() {
        var k = (sb.length() - 1) / 2;
        if (N <= k)
            count += k - N + 1;
        System.err.println(k + " " + count);
        sb.setLength(0);
    }
}
