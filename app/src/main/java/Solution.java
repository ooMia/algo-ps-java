import java.util.Map;

class Solution {

    private final Map<Character, String> compressionMap;

    Solution(Map<Character, String> compressionMap){
        this.compressionMap = compressionMap;
    }

    public String solution(String compressed, int l, int r) {
        StringBuilder sb = new StringBuilder();
        for (char ch : compressed.toCharArray()) {
            sb.append(compressionMap.get(ch));
        }
        // l과 r은 1-based 인덱스이므로, 0-based 인덱스로 변환
        return sb.substring(l - 1, r);
    }
}