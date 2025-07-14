import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Itertools<T> {

    public List<Set<T>> combinations(List<T> iterable, int r) {
        List<Set<T>> result = new ArrayList<>();
        generateCombinations(iterable, r, 0, new LinkedHashSet<>(), result);
        return result;
    }

    private void generateCombinations(List<T> iterable, int r, int start, Set<T> current, List<Set<T>> result) {
        // 원하는 크기에 도달하면 결과에 추가
        if (current.size() == r) {
            result.add(new LinkedHashSet<>(current));
            return;
        }

        // 남은 요소로 조합을 완성할 수 없으면 종료
        if (iterable.size() - start < r - current.size()) {
            return;
        }

        // start부터 끝까지 각 요소를 시도
        for (int i = start; i < iterable.size(); i++) {
            current.add(iterable.get(i));
            generateCombinations(iterable, r, i + 1, current, result);
            current.remove(iterable.get(i));
        }
    }
}