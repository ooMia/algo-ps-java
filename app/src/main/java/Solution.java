class Solution {

    final String target, replacement;

    final char[] buffer;
    int iBuffer = -1;

    Solution(String target, String replacement) {
        this.target = target;
        this.replacement = replacement;
        this.buffer = new char[target.length()];
    }

    public String solution() {
        var lenRepl = replacement.length();
        var lastChar = replacement.charAt(lenRepl - 1);
        for (char c : target.toCharArray()) {
            buffer[++iBuffer] = c;
            if (c == lastChar && check())
                iBuffer -= lenRepl;
        }

        if (iBuffer < 0) {
            return "FRULA";
        }
        return new String(buffer, 0, iBuffer + 1);
    }

    boolean check() {
        var N = replacement.length();
        for (int i = 0; i < N; ++i) {
            var j = iBuffer + 1 - N + i;
            if (j < 0)
                return false;

            var c1 = replacement.charAt(i);
            var c2 = buffer[j];
            if (c1 != c2)
                return false;
        }
        return true;
    }

}
