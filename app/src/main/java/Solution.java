import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Character, Character> dict = new HashMap<>();

    Solution() {
        dict.put('b', 'd');
        dict.put('d', 'b');
        dict.put('p', 'q');
        dict.put('q', 'p');
        dict.put('i', 'i');
        dict.put('o', 'o');
        dict.put('v', 'v');
        dict.put('w', 'w');
        dict.put('x', 'x');
    }

    public String solution(String reversed) {
        StringBuilder sb = new StringBuilder();
        for (int i = reversed.length() - 1; i >= 0; --i) {
            var c = reversed.charAt(i);
            if (!dict.containsKey(c))
                return "INVALID";
            sb.append(dict.get(c));
        }
        return sb.toString();
    }
}
