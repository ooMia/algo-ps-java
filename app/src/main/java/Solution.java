class Solution {
    public String solution(int nRows, int nCols) {
        int K = 0;
        int[][] grid = new int[nRows][nCols];
        while (fillGrid(++K, grid));
        return toResult(K - 1, grid);
    }

    private boolean fillGrid(int K, int[][] grid) {
        Point start = findNextZero(grid);
        if (start == null) return false;

        // 0인 값을 발견하면 해당 위치를 기준으로 가로/세로 2칸씩 건너뛰며 K로 채움
        for (int i = start.r; i < grid.length; i += 2) {
            for (int j = start.c; j < grid[0].length; j += 2) {
                grid[i][j] = K;
            }
        }
        return true;
    }

    class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private Point findNextZero(int[][] grid) {
        int nRows = Math.min(2, grid.length);
        int nCols = Math.min(2, grid[0].length);
        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < nCols; ++j) {
                if (grid[i][j] == 0) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    private String toResult(int K, int[][] grid) {
        StringBuilder sb = new StringBuilder();
        sb.append(K).append('\n');
        for (int[] row : grid) {
            sb.append(row[0]);
            for (int j = 1; j < row.length; ++j) {
                sb.append(' ').append(row[j]);
            }
            sb.append('\n');
        }
        return sb.toString().trim();
    }
}