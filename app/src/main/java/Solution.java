import java.util.ArrayList;
import java.util.List;

class Solution {
    final int N;
    final int[] numbers;

    public Solution(int N, int[] number) {
        this.N = N;
        this.numbers = number;
    }

    public int solution() {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            var n = numbers[i];
            int pos = findPos(res, n);
            if (pos == res.size()) {
                res.add(n);
            } else {
                res.set(pos, n);
            }
        }

        return res.size();
    }

    int findPos(List<Integer> list, int target) {
        var idx = indexedBinarySearch(list, target);
        return idx < 0 ? -(idx + 1) : idx;
    }

    // @reference Collections.binarySearch
    int indexedBinarySearch(List<Integer> list, Integer key) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Integer midVal = list.get(mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0) // midVal < key
                high = mid - 1; // assume descending order
            else if (cmp > 0)
                low = mid + 1; // assume descending order
            else
                return mid; // key found
        }
        return -(low + 1); // key not found
    }
}
