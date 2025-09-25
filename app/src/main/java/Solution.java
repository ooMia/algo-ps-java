import java.util.BitSet;

class Solution {
    String solution(String S) {
        return isPangram(S) ? "Y\n" : "N\n";
    }

    boolean isPangram(String S) {
        int nAlphas = 'z' - 'a' + 1;
        BitSet seen = new BitSet(nAlphas);
        for (char c : S.toCharArray()) {
            if (' ' == c) continue;
            seen.set(c - 'a');
        }
        return seen.cardinality() == nAlphas;
    }
}