import java.util.BitSet;

class Solution {

    public int solution(int[][] records) {
        int count = 0;

        var bs = new BitSet(200_000 + 1);
        for (var r : records) {
            int id = r[0];
            boolean enter = r[1] > 0 ? true : false;
            if (bs.get(id) == enter)
                ++count;
            bs.set(id, enter);
        }
        count += bs.cardinality();
        
        return count;
    }
}
