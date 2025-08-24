import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    final int nRows, nCols;
    final String[] grid;
    final int limitBreak;

    final byte[][] visited;
    final int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public Solution(int nRows, int nCols, String[] grid, int limitBreak) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.grid = grid;
        this.limitBreak = limitBreak;

        this.visited = new byte[nRows][nCols];
        for (int i = 0; i < nRows; ++i) {
            Arrays.fill(visited[i], (byte) -1);
        }
    }

    public int solution() {
        var pq = new PriorityQueue<Point>(Comparator.comparingInt(p -> p.dist));
        pq.offer(new Point(0, 0, 1, 0));

        while (!pq.isEmpty()) {
            var p = pq.poll();

            if (p.y == nRows - 1 && p.x == nCols - 1)
                return p.dist;

            pq.addAll(p.neighbors());
        }
        return -1;
    }

    class Point {
        final int y, x, dist;
        final byte nBreak;

        Point(int y, int x, int dist, int nBreak) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.nBreak = (byte) nBreak;
        }

        List<Point> neighbors() {
            var newDist = dist + 1;
            var points = new ArrayList<Point>();
            for (var dir : directions) {
                var newY = y + dir[0];
                var newX = x + dir[1];
                if (newY < 0 || nRows <= newY || newX < 0 || nCols <= newX)
                    continue;

                byte newBreak = (byte) (nBreak + (grid[newY].charAt(newX) == '1' ? 1 : 0));
                if (limitBreak < newBreak || visited[newY][newX] != -1 && visited[newY][newX] <= newBreak)
                    continue;

                points.add(new Point(newY, newX, newDist, newBreak));
                visited[newY][newX] = newBreak;
            }
            return points;
        }
    }
}
