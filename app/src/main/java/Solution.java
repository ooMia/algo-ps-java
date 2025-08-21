class Solution {
    public long solution(int[] numbers) {
        String s1 = String.valueOf(numbers[0]) + String.valueOf(numbers[1]);
        String s2 = String.valueOf(numbers[2]) + String.valueOf(numbers[3]);
        return Long.parseLong(s1) + Long.parseLong(s2);
    }
}
