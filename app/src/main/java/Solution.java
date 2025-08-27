class Solution {

    final String comp = "SciComLove";

    public int solution(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); ++i)
            if (comp.charAt(i) != line.charAt(i))
                ++count;
        return count;
    }
}
