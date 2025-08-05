package temp;

import java.util.List;

public class Util {
    static int[] toIntArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
