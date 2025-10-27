class Solution {
    public String solution(String[] lines) {
        var sb = new StringBuilder();
        for (String line : lines) {
            long n = Long.parseLong(line);
            if (n == 0) {
                break;
            }
            sb.append(answer(n)).append('\n');
        }
        return sb.toString();
    }
    private static long answer(long n) {
        return (n + 1) * n / 2;
    }
}