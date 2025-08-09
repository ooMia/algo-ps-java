import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {

    final int nRows;
    final char[][] baseStar = {
            { ' ', ' ', '*', ' ', ' ' },
            { ' ', '*', ' ', '*', ' ' },
            { '*', '*', '*', '*', '*' },
    };
    final char[][] stars;
    final StringBuilder sb = new StringBuilder();

    Solution(int nRows) {
        this.nRows = nRows;
        this.stars = new char[nRows][2 * nRows - 1];
        for (int i = 0; i < nRows; ++i) {
            Arrays.fill(stars[i], ' ');
        }
    }

    public String solution(int n) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, (stars[0].length + 1) / 2));

        while (queue.size() > 0) {
            Point p = queue.poll();
            draw(p.y, p.x, 0);
        }

        for (int i = 0; i < nRows; ++i) {
            sb.append(stars[i]);
            sb.append('\n');
        }
        return sb.toString();
    }

    void draw(int y, int x, int level) {
        if (level <= 1) {
            
        }

    }

    class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}