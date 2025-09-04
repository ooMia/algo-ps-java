class Solution {
    final int id, o;
    int w, status = 1;

    /**
     * @param id 시나리오 번호
     * @param o  적정 체중
     * @param w  현재 체중
     */
    Solution(int id, int o, int w) {
        this.id = id;
        this.o = o;
        this.w = w;
    }

    void solution(char behavior, int value) {
        if (status < 0) // Dead
            return;
        switch (behavior) {
            case 'F': // Increase weight
                w += value;
                break;
            case 'E': // Decrease weight
                w -= value;
                break;
        }

        if (w <= 0) // Dead
            status = -1;
        else if (o / 2 < w && w < o * 2) // Success
            status = 1;
        else // Fail
            status = 0;
    }

    String status() {
        return switch (status) {
            case -1 -> "RIP";
            case 0 -> ":-(";
            default -> ":-)";
        };
    }

    @Override
    public String toString() {
        return String.format("%d %s", id, status());
    }
}