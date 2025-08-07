class Solution {

    public String solution(String str) {
        int[] charMap = new int['z' - 'a' + 1];
        for (char c : str.toCharArray()) {
            charMap[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charMap.length; ++i) {
            sb.append(charMap[i]).append(' ');
        }
        return sb.toString();
    }

}