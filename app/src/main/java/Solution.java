class Solution {
    public int solution(String time) {
        var cases = new boolean[3][3];

        var s = time.split(":");
        for (int i = 0; i < 3; ++i) {
            var t = Integer.parseInt(s[i]);
            var c = cases[i];
            if (isHour(t)) {
                c[0] = true;
            }
            if (isMinuteOrSecond(t)) {
                c[1] = true;
                c[2] = true;
            }
        }

        var combinations = new int[][] {
                new int[] { 0, 1, 2 },
                new int[] { 0, 2, 1 },
                new int[] { 1, 0, 2 },
                new int[] { 1, 2, 0 },
                new int[] { 2, 0, 1 },
                new int[] { 2, 1, 0 }
        };
        int count = 0;
        for (var comb : combinations) {
            if (cases[0][comb[0]] && cases[1][comb[1]] && cases[2][comb[2]])
                ++count;
        }
        return count;
    }

    boolean isHour(int time) {
        return time >= 1 && time <= 12;
    }

    boolean isMinuteOrSecond(int time) {
        return time >= 0 && time < 60;
    }
}
