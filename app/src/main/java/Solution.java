class Solution {
    final String from, to;

    Solution(String from, String to) {
        this.from = from;
        this.to = to;
    }

    int solution() {
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i + from.length() <= to.length(); ++i) {
            System.err.println(i);
            minDiff = Math.min(minDiff, difference(i));
        }
        return minDiff;
    }

    int difference(int iStart) {
        // iStart + from.length() <= to.length()를 만족하는 iStart에 대해
        // to.subStr(iStart, to.length())과 from의 차이를 구한다.
        int diff = 0;
        for (int i = 0; i < from.length(); ++i)
            if (from.charAt(i) != to.charAt(iStart + i))
                ++diff;
        return diff;
    }
}