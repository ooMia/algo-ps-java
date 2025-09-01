import java.util.Collections;
import java.util.List;

class Solution {
    final int N;
    final List<int[]> monitors;

    Solution(int N, List<int[]> monitors) {
        this.N = N;
        this.monitors = monitors;
    }

    public String solution() {
        Collections.sort(monitors, (o1, o2) -> {
            var cmp = Integer.compare(diagSq(o2), diagSq(o1));
            if (cmp != 0)
                return cmp;
            return Integer.compare(o1[2], o2[2]);
        });
        var sb = new StringBuilder();
        for (var monitor : monitors) {
            sb.append(monitor[2]).append('\n');
        }
        return sb.toString();
    }

    int diagSq(int[] monitor) {
        return monitor[0] * monitor[0] + monitor[1] * monitor[1];
    }
}