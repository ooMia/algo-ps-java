class Solution {
    public String solution(String lines[]) {
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            if ("#".equals(line)) {
                break;
            }
            sb.append(countVowels(line)).append('\n');
        }
        return sb.toString();
    }

    int countVowels(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if ("aeiouAEIOU".indexOf(c) >= 0)
                ++count;
        }
        return count;
    }
}
