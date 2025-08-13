import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

class Solution {
    public String solution(int[] heights) {
        int total = Arrays.stream(heights).sum();
        int expected = total - 100; // 두 숫자의 합에 대한 기댓값
        Set<Integer> set = new TreeSet<>(Arrays.stream(heights).boxed().collect(Collectors.toSet()));
        
        for (var n : heights) {
            if (set.contains(expected - n) && n != expected - n) {
                set.remove(n);
                set.remove(expected - n);
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (var n : set) {
            sb.append(n).append('\n');
        }
        return sb.toString();
    }
}