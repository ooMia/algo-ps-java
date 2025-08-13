class Solution {
    public String solution(String s) {
        char[] arr = new char[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            arr[i] = ROT13(s.charAt(i));
        }
        return new String(arr);
    }

    char ROT13(char c) {
        if (Character.isDigit(c) || c == ' ') {
            return c;
        }
        int base = Character.isLowerCase(c) ? 'a' : 'A';
        return (char) ((c - base + 13) % 26 + base);
    }
}
