class Solution {

    final String[] lines;

    Solution(String[] lines) {
        this.lines = lines;
    }

    public String solution() {
        char[] res = lines[0].toCharArray();
        for (int i = 1; i < lines.length; ++i) {
            char[] next = lines[i].toCharArray();
            for (int j = 0; j < res.length; ++j) {
                if (res[j] != next[j])
                    res[j] = '?';
            }
        }
        return new String(res);
    }

}
