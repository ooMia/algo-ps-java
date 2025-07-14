import java.util.ArrayList;
import java.util.List;

class Util {
    static List<Integer> range(int first, int last) {
        List<Integer> result = new ArrayList<>();
        for (int i = first; i <= last; ++i) {
            result.add(i);
        }
        return result;
    }
}
