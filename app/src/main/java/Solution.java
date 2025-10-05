import java.util.SortedMap;

class Solution {

    public int solution(int N, int[] A, int[] B) {
        // A_i: i번 학생이 교환하고 싶은 수업의 번호 (from)
        // B_i: i번 학생이 교환 받고 싶은 수업의 번호 (to)

        SortedMap<Pair, Integer> map = new java.util.TreeMap<>();
        for (int i = 0; i < N; ++i) {
            var p = new Pair(A[i], B[i]);
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        int count = 0;
        while (!map.entrySet().isEmpty()) {
            var iter = map.entrySet().iterator();
            var e = iter.next();
            var key = e.getKey();
            var value = e.getValue();
            count += value;
            map.remove(key);
            if (value == 0) continue;

            // 후보 찾기
            // 1. (a, b) -> (b, a)
            Pair candidateKey = new Pair(key.to, key.from);
            if (!map.containsKey(candidateKey)) {
                // 2. alternative: (a, b) -> (b, c)
                var candidates = map.tailMap(new Pair(key.to, 1));
                if (candidates.isEmpty()) continue;
                candidateKey = candidates.firstKey();
            }

            var candidateValue = map.get(candidateKey);
            if (key.to != candidateKey.from) continue;

            // swap
            int nPossible = Math.min(value, candidateValue);
            count -= nPossible;

            // update
            {
                var v1 = value - nPossible;
                if (v1 > 1) map.put(key, v1);
                else map.remove(key);

                var v2 = candidateValue - nPossible;
                if (v2 > 1) map.put(candidateKey, v2);
                else map.remove(candidateKey);
            }

            if (key.from == candidateKey.to) continue;
            var newKey = new Pair(key.from, candidateKey.to);
            map.put(newKey, map.getOrDefault(newKey, 1) + value + candidateValue - 2 * nPossible);
        }
        return count;
    }

    class Pair implements Comparable<Pair> {
        final int from, to;

        Pair(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Pair o) {
            int cmp = Integer.compare(from, o.from);
            if (cmp != 0) return cmp;
            return Integer.compare(to, o.to);
        }

        @Override
        public String toString() {
            return String.format("[%d,%d]", from, to);
        }
    }
}
