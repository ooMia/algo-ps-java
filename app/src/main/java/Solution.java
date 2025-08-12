import java.util.Set;
import java.util.TreeSet;

class Solution {

    public String solution(String word) {
        Set<String> suffs = new TreeSet<>();
        for (int i = 0; i < word.length(); ++i) {
            suffs.add(word.substring(i));
        }

        StringBuilder sb = new StringBuilder();
        for (String s : suffs) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }
}
