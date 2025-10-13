class Solution {

    public String solution(char c, String line) {
        char upper = Character.toUpperCase(c);
        char lower = Character.toLowerCase(c);

        int count = 0;
        for (char target : line.toCharArray())
            if (target == upper || target == lower) ++count;
        return String.format("%c %d\n", c, count);
    }
}