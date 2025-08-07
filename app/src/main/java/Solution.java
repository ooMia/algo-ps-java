import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    final int nRows, nCols;
    final byte[][] grid;

    final int nBlanks;
    final List<Point> spaces = new ArrayList<>();
    final List<Point> viruses = new ArrayList<>();

    Solution(int nRows, int nCols, byte[][] grid) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.grid = grid;

        // Count the number of empty spaces
        int count = 0;
        for (int r = 0; r < nRows; ++r) {
            for (int c = 0; c < nCols; ++c) {
                if (grid[r][c] == 0) {
                    spaces.add(new Point(r, c));
                    ++count;
                } else if (grid[r][c] == 2) {
                    viruses.add(new Point(r, c));
                }
            }
        }
        this.nBlanks = count;
    }

    public int solution() {
        // 0: 빈 칸, 1: 벽, 2: 바이러스
        // 바이러스는 가로 또는 세로로 인접한 칸으로 퍼질 수 있다.
        // 3개의 벽을 추가한 후 바이러스가 퍼지는 시뮬레이션을 수행한다.
        // 바이러스가 퍼진 후, 빈 칸의 개수가 최대가 되는 경우를 찾아야 한다.

        // 1. 벽을 설치할 수 있는 모든 조합에 대해 바이러스가 퍼진 후 빈 칸의 개수를 계산한다.
        // 2. 빈 칸의 개수가 최대가 되는 경우를 찾아서 반환한다.
        final int wLimit = spaces.size();
        int maxBlanks = -1;
        for (int w1 = 0; w1 < wLimit - 2; ++w1) {
            for (int w2 = w1 + 1; w2 < wLimit - 1; ++w2) {
                for (int w3 = w2 + 1; w3 < wLimit; ++w3) {
                    // 3개의 벽을 설치한 후 바이러스가 퍼지는 시뮬레이션을 수행한다.
                    var p1 = spaces.get(w1);
                    var p2 = spaces.get(w2);
                    var p3 = spaces.get(w3);

                    byte[][] _grid = clone(grid);
                    _grid[p1.y][p1.x] = 1;
                    _grid[p2.y][p2.x] = 1;
                    _grid[p3.y][p3.x] = 1;

                    int nBlanksAfter = simulate(_grid, viruses);
                    // if (nBlanksAfter > maxBlanks) {
                    //     for (var row : _grid) {
                    //         System.err.println(Arrays.toString(row));
                    //     }
                    //     System.err.println();
                    // }
                    maxBlanks = Math.max(maxBlanks, nBlanksAfter);
                }
            }
        }
        return maxBlanks;
    }

    class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        byte get(byte[][] grid) {
            return grid[y][x];
        }
    }

    byte[][] clone(byte[][] grid) {
        byte[][] copy = new byte[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        return copy;
    }

    int simulate(byte[][] grid, List<Point> viruses) {

        // 바이러스가 퍼지는 시뮬레이션을 수행
        // 남은 빈 칸 개수를 반환
        int nBlanks = this.nBlanks;
        Queue<Point> queue = new LinkedList<>();
        for (var virus : viruses) {
            queue.add(new Point(virus.y - 1, virus.x)); // 위
            queue.add(new Point(virus.y + 1, virus.x)); // 아래
            queue.add(new Point(virus.y, virus.x - 1)); // 왼쪽
            queue.add(new Point(virus.y, virus.x + 1)); // 오른쪽
        }

        while (queue.size() > 0) {
            Point p = queue.poll();
            if (p.y < 0 || p.y >= nRows || p.x < 0 || p.x >= nCols) {
                continue; // 범위를 벗어난 경우
            }

            var v = p.get(grid);
            if (v == 0) {
                grid[p.y][p.x] = 2; // 바이러스가 퍼짐
                --nBlanks; // 빈 칸이 하나 줄어듦

                queue.add(new Point(p.y - 1, p.x)); // 위
                queue.add(new Point(p.y + 1, p.x)); // 아래
                queue.add(new Point(p.y, p.x - 1)); // 왼쪽
                queue.add(new Point(p.y, p.x + 1)); // 오른쪽
            }
        }

        return nBlanks - 3; // 3개의 벽을 설치했으므로 빈 칸 개수에서 3을 뺀다.
    }
}