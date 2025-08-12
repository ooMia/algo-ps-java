class Solution {

    public String solution(String[] ls) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ls.length; ++i) {
            sb.append(i + 1).append(". ").append(ls[i]).append('\n');
        }
        return sb.toString();
    }

}
