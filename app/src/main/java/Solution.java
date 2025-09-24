class Solution {
    private final String bus = "Bus", subway = "Subway", anything = "Anything";

    String solution(int N, int A, int B) {
        if (A == B) return anything;
        return A < B ? bus : subway;
    }
}