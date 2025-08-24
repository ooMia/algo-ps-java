import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    final List<Set<Integer>> edges;
    final Set<Integer> visited;
    final int[] counts;

    Solution(int nNodes, List<Set<Integer>> edges, int rootId) {
        this.edges = edges;
        this.visited = new HashSet<>(nNodes);
        this.counts = new int[nNodes + 1];
        count(rootId);
    }

    private void count(int rootId) {
        var children = edges.get(rootId);
        counts[rootId] = 1;
        visited.add(rootId);
        for (var child : children) {
            if (visited.contains(child))
                continue;
            count(child);
            counts[rootId] += counts[child];
        }
    }

    public int solution(int queryId) {
        return counts[queryId];
    }
}