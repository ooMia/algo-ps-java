class Solution {
    int solution(String line) {
        int base = getBase(line);
        line = line.substring(base == 16 ? 2 : base == 8 ? 1 : 0);
        return Integer.parseInt(line, base);
    }

    private int getBase(String s) {
        if (s.length() > 2 && "0x".equals(s.substring(0, 2))) return 16;
        if (s.length() > 1 && s.charAt(0) == '0') return 8;
        return 10;
    }
}