import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {

    final int nRows, nCols;
    final String[] grid;
    final boolean[] keys;

    final boolean[][] isInQueue;
    final List<Queue<int[]>> queues;

    final int[] dY = { -1, 1, 0, 0 };
    final int[] dX = { 0, 0, -1, 1 };

    private int count = 0;
    private boolean gotNewKey = true;

    Solution(int h, int w, String[] grid, boolean[] keys) {
        this.nRows = h;
        this.nCols = w;
        this.grid = grid;
        this.keys = keys;

        this.queues = List.of(new ArrayDeque<>(), new ArrayDeque<>());
        this.isInQueue = new boolean[nRows][nCols];
    }

    public int solution(boolean[] keys) {
        var currentQ = queues.get(1);
        var nextQ = queues.get(0);

        initQueue(nextQ); // 테두리를 탐색하여 벽이 아닌 모든 셀 큐에 추가

        // 2. BFS 탐색
        // 현재 큐에서 탐색을 진행하며
        // '통과하지 못한 문'은 다음 큐에 추가
        // 중간에 새로운 열쇠를 획득하면 기록하고
        // 현재 trial에 새로운 열쇠를 획득했고, 다음 큐가 남아 있는 경우에만 탐색을 지속

        while (gotNewKey && !nextQ.isEmpty()) {
            {
                var temp = currentQ;
                currentQ = nextQ;
                nextQ = temp;
                gotNewKey = false;
            }

            while (!currentQ.isEmpty()) {
                var pos = currentQ.poll();
                System.err.println(Arrays.toString(pos) + " " + getCell(pos[0], pos[1]));
                int y = pos[0], x = pos[1];
                char c = getCell(y, x);
                if (isAccessible(y, x)) {
                    handleCell(c);
                } else if (Character.isUpperCase(c)) {
                    addQueue(nextQ, y, x);
                    continue;
                }

                // 상하좌우 탐색
                for (int d = 0; d < 4; d++) {
                    int ny = y + dY[d];
                    int nx = x + dX[d];
                    if (isInBounds(ny, nx) && !isInQueue[ny][nx] && getCell(ny, nx) != '*') {
                        addQueue(currentQ, ny, nx);
                    }
                }
            }
        }

        return count;
    }

    boolean isInBounds(int y, int x) {
        return 0 <= y && y < nRows && 0 <= x && x < nCols;
    }

    char getCell(int y, int x) {
        return grid[y].charAt(x);
    }

    void initQueue(Queue<int[]> q) {
        // 테두리를 탐색하여 벽이 아닌 모든 셀 큐에 추가
        int yMin = 0, yMax = nRows - 1, xMin = 0, xMax = nCols - 1;
        for (int y = 0; y < nRows; ++y) {
            if (y == yMin || y == yMax) {
                for (int x = 0; x < nCols; ++x) {
                    char c = getCell(y, x);
                    if (c != '*')
                        addQueue(q, y, x);
                }
            } else {
                if (getCell(y, xMin) != '*')
                    addQueue(q, y, xMin);
                if (getCell(y, xMax) != '*')
                    addQueue(q, y, xMax);
            }
        }
    }

    private void addQueue(Queue<int[]> q, int y, int x) {
        q.add(new int[] { y, x });
        isInQueue[y][x] = true;
    }

    boolean isAccessible(int y, int x) {
        char cell = grid[y].charAt(x);
        if (cell == '*') {
            return false;
        }
        if (Character.isUpperCase(cell)) {
            return keys[cell - 'A'];
        }
        return true;
    }

    void handleCell(char c) {
        if ('$' == c)
            this.count += 1;
        else if (Character.isLowerCase(c)) {
            keys[c - 'a'] = true;
            gotNewKey = true;
        }
    }
}
