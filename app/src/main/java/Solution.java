import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    final int N;
    final int[] numbers;

    public Solution(int N, int[] number) {
        this.N = N;
        this.numbers = number;
    }

    public int solution() {
        List<Integer> res = new ArrayList<>();

        for (var n : numbers) {
            int idx = findPos(res, n);
            if (idx == res.size()) {
                res.add(n);
            } else {
                res.set(idx, n);
            }
        }
        return res.size();
    }

    int findPos(List<Integer> list, int target) {
        // idx는 동일한 요소가 없는 경우, 자신보다 큰 요소가 처음으로 나오는 위치를 반환함
        var idx = Collections.binarySearch(list, target);
        return idx < 0 ? -(idx + 1) : idx;
    }
}
