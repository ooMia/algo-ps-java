import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    final int n;
    final int[] squares;

    Solution(int n) {
        this.n = n;

        int l = (int) Math.sqrt(n);
        squares = new int[l];
        for (int k = 1; k <= l; ++k)
            squares[k - 1] = k * k;
    }

    int solution() {
        PriorityQueue<Task> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.count));
        pq.offer(new Task(n, 0));

        while (!pq.isEmpty()) {
            Task curr = pq.poll();
            if (curr.n == 0)
                return curr.count;

            int iStart = Arrays.binarySearch(squares, curr.n / 3);
            iStart = iStart < 0 ? -(iStart + 1) : iStart;
            for (int i = iStart; i < squares.length; ++i) {
                int square = squares[i];
                if (square > curr.n)
                    break;
                pq.offer(new Task(curr.n - square, curr.count + 1));
            }
        }
        return -1;
    }

    class Task {
        int n, count;

        Task(int n, int count) {
            this.n = n;
            this.count = count;
        }
    }
}