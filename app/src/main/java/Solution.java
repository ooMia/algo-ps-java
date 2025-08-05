class Solution {
    private int hour = 1; // [1, 12]
    private int modifier = +1; // +1 or -1

    final StringBuilder sb = new StringBuilder();

    public String solution(int N, Card[] cards) {
        sb.ensureCapacity(20 * N);

        for (int i = 0; i < N; ++i) {
            var c = cards[i];
            applyRules(c);
            turnOver();
        }

        return sb.toString();
    }

    void applyRules(Card c) {
        sb.append(hour).append(' ');

        boolean needSync = false;
        if (c.isHourglass && c.hour == hour) {
            // 과부하의 원칙
        } else if (c.isHourglass) {
            // 시간 역행의 법칙
            modifier *= -1;
        } else if (c.hour == hour) {
            // 동기화의 원칙
            needSync = true;
        }
        sb.append(needSync ? "YES" : "NO").append('\n');
    }

    void turnOver() {
        hour += modifier;
        if (hour == 0) hour = 12;
        else if (hour == 13) hour = 1;
    }
}