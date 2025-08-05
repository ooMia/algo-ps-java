import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {

    final int N, K; // N: 수빈이의 위치, K: 동생의 위치

    Solution(int N, int K) {
        this.N = N;
        this.K = K;
    }

    public long solve() {
        PriorityQueue<History> pq = new PriorityQueue<>();
        Map<Integer, Integer> visited = new HashMap<>();
        pq.offer(new History(0, N));
        visited.put(N, 0);

        while (!pq.isEmpty()) {
            History current = pq.poll();
            int t = current.time;
            int p = current.pos;

            if (p == K) {
                return t;
            }

            // 다음 위치를 큐에 추가
            if (p - 1 >= 0) {
                var key = p - 1;
                if (!visited.containsKey(key) || visited.get(key) > t + 1) {
                    pq.offer(new History(t + 1, key));
                    visited.put(key, t + 1);
                }
            }

            if (p + 1 <= 100000) {
                var key = p + 1;
                if (!visited.containsKey(key) || visited.get(key) > t + 1) {
                    pq.offer(new History(t + 1, key));
                    visited.put(key, t + 1);
                }
            }
            if (p * 2 <= 100000) {
                var key = p * 2;
                if (!visited.containsKey(key) || visited.get(key) > t) {
                    pq.offer(new History(t, key));
                    visited.put(key, t);
                }
            }
            System.err.println(pq.size());
        }
        return -1;
    }

    class History implements Comparable<History> {
        final int time, pos;

        History(int time, int pos) {
            this.time = time;
            this.pos = pos;
        }

        @Override
        public int compareTo(History o) {
            return Integer.compare(this.time, o.time);
        }
    }
}