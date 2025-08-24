import java.util.BitSet;
import java.util.Set;

class Solution {
    final Set<Integer>[] edges;
    final BitSet visited;
    final int[] counts;

    Solution(int nNodes, Set<Integer>[] edges, int rootId) {
        this.edges = edges;
        this.visited = new BitSet(nNodes + 1);
        this.counts = new int[nNodes + 1];
        count(rootId);
    }

    private void count(int rootId) {
        var children = edges[rootId];
        counts[rootId] = 1;
        visited.set(rootId);
        for (var child : children) {
            if (visited.get(child)) continue;
            count(child);
            counts[rootId] += counts[child];
        }
    }

    public int solution(int queryId) {
        return counts[queryId];
    }
}