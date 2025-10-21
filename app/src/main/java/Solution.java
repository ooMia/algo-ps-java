import java.util.HashMap;
import java.util.Map;

class Solution {

    Map<Integer, Integer> fromOldToNewMap;

    Solution(int[] newMapping) {
        fromOldToNewMap = new HashMap<>();
        for (int i = 0; i < newMapping.length; i++) {
            fromOldToNewMap.put(newMapping[i], i + 1);
        }
    }

    public String solution(String result) {
        StringBuilder sb = new StringBuilder();
        var keyPressed = new OldPhone.KeyPress(0,0);
        for (var c : result.toCharArray()) {
            var keyWillPress = OldPhone.CHAR_TO_KEYPRESS_MAP.get(c);
            if (keyPressed.key == keyWillPress.key) {
                sb.append('#');
            }

            keyPressed = keyWillPress;
            int oldKey = keyPressed.key;
            int count = keyPressed.count;
            int newKey = fromOldToNewMap.get(oldKey);
            for (int i = 0; i < count; i++) {
                sb.append(newKey);
            }
        }
        return sb.toString();

    }

    static class OldPhone {
        // ex. 2를 세 번 누르면 'c'가 입력된다.
        static final char[][] KEYS = new char[][]{
                {},
                {},
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };

        static final Map<Character, KeyPress> CHAR_TO_KEYPRESS_MAP = new HashMap<>();
        static {
            for (int key = 2; key <= 9; key++) {
                for (int count = 1; count <= KEYS[key].length; count++) {
                    char ch = KEYS[key][count - 1];
                    CHAR_TO_KEYPRESS_MAP.put(ch, new KeyPress(key, count));
                }
            }
        }

        public static char getChar(int key, int count) {
            return KEYS[key][count - 1];
        }

        static class KeyPress {
            final int key, count;

            KeyPress(int key, int count) {
                this.key = key;
                this.count = count;
            }
        }

    }
}