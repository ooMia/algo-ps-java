import java.util.BitSet;

class Solution {
    final int N;
    final BitSet positions;

    final int[] dy = { -1, -1, 1, 1 };
    final int[] dx = { -1, 1, -1, 1 };

    Solution(int N, BitSet positions) {
        this.N = N;
        this.positions = positions;
    }

    public int solution() {
        if (N == 1)
            return positions.get(0) ? 1 : 0;
        int maxBishop = 0, i = positions.nextSetBit(0);
        while (i >= 0) {
            int res = dfs(i, 1);
            maxBishop = Math.max(maxBishop, res);
            i = positions.nextSetBit(i + 1);
        }
        return maxBishop;
    }

    int dfs(int idx, final int depth) {
        if (idx < 0 || idx >= N * N)
            return depth;

        var removed = putBishop(idx);

        int i = positions.nextSetBit(idx + 1);
        int maxDepth = depth;
        while (i >= 0) {
            var removed2 = putBishop(i);
            int res = dfs(i, depth + 1);
            resetBoard(removed2);

            maxDepth = Math.max(maxDepth, res);
            i = positions.nextSetBit(i + 1);
        }

        resetBoard(removed);

        return maxDepth;
    }

    BitSet putBishop(int pos) {
        BitSet removed = new BitSet(N * N);
        removed.set(pos);

        int row = pos / N;
        int col = pos % N;

        // 각 대각선 방향으로 탐색
        for (int dir = 0; dir < 4; dir++) {
            int r = row + dy[dir];
            int c = col + dx[dir];

            while (r >= 0 && r < N && c >= 0 && c < N) {
                int idx = r * N + c;
                if (positions.get(idx)) {
                    removed.set(idx);
                }
                r += dy[dir];
                c += dx[dir];
            }
        }

        positions.andNot(removed); // clear positions
        return removed;
    }

    void resetBoard(BitSet removed) {
        positions.or(removed);
    }
}