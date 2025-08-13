import java.util.List;
import java.util.PriorityQueue;

class Solution {

    final int nRows, nCols;
    final char[][] grid;
    final boolean[][] visited;
    final int[][] dist;

    final PriorityQueue<Point> queue = new PriorityQueue<>(
            (o1, o2) -> Integer.compare(o1.dist, o2.dist));

    Solution(int nRows, int nCols, char[][] grid, int rDest, int cDest) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.grid = grid;
        this.visited = new boolean[nRows][nCols];
        this.dist = new int[nRows][nCols];

        queue.add(new Point(rDest, cDest, 0));
        visited[rDest][cDest] = true;
    }

    public String solution() {
        while (!queue.isEmpty()) {
            var p = queue.poll();
            dist[p.y][p.x] = p.dist;

            var nexts = List.of(
                    new Point(p.y, p.x - 1, p.dist + 1), // left
                    new Point(p.y, p.x + 1, p.dist + 1), // right
                    new Point(p.y - 1, p.x, p.dist + 1), // down
                    new Point(p.y + 1, p.x, p.dist + 1)  // up
            );
            for (var next : nexts) {
                int Y = next.y, X = next.x;
                if (isValidPos(Y, X) && visited[Y][X] == false && grid[Y][X] == '1') {
                    visited[Y][X] = true;
                    queue.add(next);
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < nRows; ++row) {
            for (int col = 0; col < nCols; ++col) {
                int d = (grid[row][col] == '1' && dist[row][col] == 0) ? -1 : dist[row][col];
                sb.append(d).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    boolean isValidPos(int y, int x) {
        return 0 <= y && y < nRows && 0 <= x && x < nCols;
    }

    class Point {
        final int y, x, dist;

        Point(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

}