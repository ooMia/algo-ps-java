class Solution {
    final String target = "2023";

    int solution(int N) {
        int res = 0;
        for (int i = 2022; i <= N; ++i) {
            var s = String.valueOf(i);
            if (isTarget(s))
                ++res;
        }
        return res;
    }

    boolean isTarget(String s) {
        int prevIdx = 0;
        for (int t = 0; t < target.length(); ++t) {
            var idx = s.substring(prevIdx).indexOf(target.charAt(t));
            if (idx == -1)
                return false;
            prevIdx += idx + 1;
        }
        return true;
    }
}