import java.util.LinkedList;
import java.util.Queue;

class Solution {
    final int nRows, nCols;
    final String[] grid;
    final int limitBreak;

    final int[][][] visited; // [부순 벽의 수][행][열] = 거리
    final int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public Solution(int nRows, int nCols, String[] grid, int limitBreak) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.grid = grid;
        this.limitBreak = limitBreak;

        this.visited = new int[limitBreak + 1][nRows][nCols];
    }

    public int solution() {
        Queue<Point> q = new LinkedList<>();

        q.offer(new Point(0, 0, 1, 0));
        visited[0][0][0] = 1;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.y == nRows - 1 && p.x == nCols - 1) {
                return p.dist;
            }

            int newDist = p.dist + 1;

            for (var dir : directions) {
                int newY = p.y + dir[0];
                int newX = p.x + dir[1];

                if (newY < 0 || nRows <= newY || newX < 0 || nCols <= newX)
                    continue; // 좌표 유효성 판단

                if (grid[newY].charAt(newX) == '0') { // 경우 1: 벽이 아닌 곳으로
                    if (!isVisited(p.nBreak, newY, newX)) {
                        visited[p.nBreak][newY][newX] = newDist;
                        q.offer(new Point(newY, newX, newDist, p.nBreak));
                    }
                }
                // 경우 2: 벽을 만남
                else {
                    var newBreak = p.nBreak + 1;
                    // 낮이고, 벽을 더 부술 수 있고, 해당 상태로 방문한 적 없다면
                    if (p.isDay() && newBreak <= limitBreak && !isVisited(newBreak, newY, newX)) {
                        visited[newBreak][newY][newX] = newDist;
                        q.offer(new Point(newY, newX, newDist, newBreak));
                    }
                }
            }

            // 경우 3: 제자리에서 대기
            // 최초 방문 상황에서만 대기를 고려한다.
            if (!p.isDay() && visited[p.nBreak][p.y][p.x] == p.dist) {
                visited[p.nBreak][p.y][p.x] = newDist;
                q.offer(new Point(p.y, p.x, newDist, p.nBreak));
            }
        }
        return -1;
    }

    boolean isVisited(int nBreak, int y, int x) {
        return visited[nBreak][y][x] > 0;
    }

    class Point {
        final int y, x, dist, nBreak;

        Point(int y, int x, int dist, int nBreak) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.nBreak = nBreak;
        }

        boolean isDay() {
            return dist % 2 == 1;
        }
    }
}