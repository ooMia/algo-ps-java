class Solution {

    public int solution(char[] binary) {
        char prev = binary[0];
        int i = 1;

        int set0 = (prev == '0') ? 1 : 0;
        int set1 = (prev == '1') ? 1 : 0;

        while (i < binary.length) {
            var c = binary[i++];
            if (c == prev)
                continue;

            if (c == '0')
                set0++;
            else
                set1++;
            prev = c;
        }
        return Math.min(set0, set1);
    }
}
