class Solution {

    final String[] tokens;

    Solution(String[] tokens) {
        this.tokens = tokens;
    }

    public int solution() {
        int res = Integer.parseInt(tokens[0]);
        for (int i = 1; i < tokens.length;) {
            int sub = 0;
            {
                int n = Integer.parseInt(tokens[i++]);
                if (n > 0) {
                    res += n;
                    continue;
                }
                sub = n;
            }

            while (i < tokens.length) {
                int m = Integer.parseInt(tokens[i]);
                if (m < 0)
                    break;
                sub -= m;
                ++i;
            }
            res += sub;
        }
        return res;
    }

}
