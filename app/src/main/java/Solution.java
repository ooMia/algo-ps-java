import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class Solution {
    final int N;
    final int[] numbers;

    public Solution(int N, int[] number) {
        this.N = N;
        this.numbers = number;
    }

    public String solution() {
        List<Integer> res = new ArrayList<>();
        List<Integer> index = new ArrayList<>();

        int[] prev = new int[N];
        Arrays.fill(prev, -1);

        for (int i = 0; i < N; ++i) {
            var n = numbers[i];
            int pos = findPos(res, n);
            if (pos == res.size()) {
                res.add(n);
                index.add(i);
            } else {
                res.set(pos, n);
                index.set(pos, i);
            }
            if (pos > 0) {
                prev[i] = index.get(pos - 1);
            }
        }

        var sb = new StringBuilder();
        sb.append(res.size()).append("\n");
        var stack = new Stack<Integer>();
        int idx = index.get(index.size() - 1);
        while (idx != -1) {
            stack.push(numbers[idx]);
            idx = prev[idx];
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString();
    }

    int findPos(List<Integer> list, int target) {
        // idx는 동일한 요소가 없는 경우, 자신보다 큰 요소가 처음으로 나오는 위치
        var idx = Collections.binarySearch(list, target);
        return idx < 0 ? -(idx + 1) : idx;
    }
}