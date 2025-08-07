import java.util.stream.Stream;

class Solution {

    public String solution(Stream<String> input) {
        StringBuilder sb = new StringBuilder();
        input.forEach(line -> {
            sb.append(line).append('\n');
        });
        return sb.toString();
    }
}