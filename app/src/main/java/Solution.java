class Solution {
    public String solution(int[][] pairs) {
        var sb = new StringBuilder();
        for (var pair : pairs) {
            sb.append(pair[1] / (pair[0] + 1)).append('\n');
        }
        return sb.toString().trim();
    }
}