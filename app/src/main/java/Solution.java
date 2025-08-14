import java.util.Collection;
import java.util.List;
import java.util.Stack;

class Solution {

    final int N; // grid size: 2^N * 2^N
    final int r, c; // dest coords
    final Stack<Board> stack = new Stack<>();

    Solution(int N, int r, int c) {
        this.N = N;
        this.r = r;
        this.c = c;
        stack.push(new Board(0, 0, 1 << N));
    }

    public int solution() {
        int count = 0;
        while (!stack.isEmpty()) {
            var board = stack.pop();
            if (!board.contains(r, c)) {
                count += board.size();
            } else if (board.len > 1) {
                stack.addAll(board.split());
            } else {
                break;
            }
        }
        return count;
    }

    class Board {
        final int minX, minY, len;

        Board(int minX, int minY, int len) {
            this.minX = minX;
            this.minY = minY;
            this.len = len;
        }

        boolean contains(int r, int c) {
            return minX <= c && c < minX + len && minY <= r && r < minY + len;
        }

        int size() {
            return len * len;
        }

        Collection<? extends Solution.Board> split() {
            int half = len / 2;
            return List.of( // reversed Z-order
                    new Board(minX + half, minY + half, half),
                    new Board(minX, minY + half, half),
                    new Board(minX + half, minY, half),
                    new Board(minX, minY, half));
        }
    }

}