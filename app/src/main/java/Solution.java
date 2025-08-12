class Solution {

    public String solution(String[] words) {
        StringBuilder sb = new StringBuilder();

        for (String s : words) {
            StringBuilder word = new StringBuilder(s);
            sb.append(word.reverse().toString()).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
