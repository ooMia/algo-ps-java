import java.util.Arrays;

class Solution {

    final int[][] times;

    Solution(int[][] times) {
        this.times = times;
    }

    public int solution() {
        Arrays.sort(times, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int t = 0, count = 0;
        for (int[] time : times) {
            System.err.println("t: " + t + ", time: " + Arrays.toString(time));
            if (t <= time[0]) {
                t = time[1];
                ++count;
            }
        }

        return count;
    }

}
