class Solution {
    // (a+b, b) 또는 (a, a+b) 규칙에 따라 증식하는 이진 트리

    // a, b는 모두 자연수이기 때문에, 결과는 항상 비대칭적으로 나올 수 밖에 없다.
    // (a+b == b이기 위한 조건은 a == 0이고, a+b == a이기 위한 조건은 b == 0이다.)

    // (x, y)가 주어졌을 때, 이 값을 나오도록 한 부모 노드는 x와 y간의 대수 관계에 의해 결정적으로 정해진다.

    private int L, R;

    Solution(int L, int R) {
        this.L = L;
        this.R = R;
    }

    String solution() {
        int countL = 0, countR = 0;
        while (!(L == 1 && R == 1)) {
            if (L > R) {
                L = L - R;
                countL++;
            } else {
                R = R - L;
                countR++;
            }
        }
        return String.format("%d %d", countL, countR);
    }
}