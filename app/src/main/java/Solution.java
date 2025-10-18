import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    static class Graph {

        private final int maxNodeId;
        private final int[][] adj;

        Graph(int destId, int[][] paths) {
            // (x, y) = paths[0], paths[1]
            // 1 <= x < y <= N
            this.maxNodeId = destId;
            this.adj = paths;
            Arrays.sort(adj, (a, b) -> {
                if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            });
        }

        List<Integer> getNeighbors(int nodeId) {
            // find neighbors of nodeId
            // 1. find minimal index using binary search
            // 2. collect neighbors until nodeId changes

            int left = 0, right = adj.length - 1;
            while (left <= right) {
                int adder = right - left >= 2 ? (right - left) >>> 1 : 0;
                int mid = left + adder;
                if (adj[mid][0] < nodeId) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (left >= adj.length || adj[left][0] != nodeId) {
                return List.of();
            }

            List<Integer> neighbors = new ArrayList<>();
            for (int i = left; i < adj.length && adj[i][0] == nodeId; ++i) {
                neighbors.add(adj[i][1]);
            }
            return neighbors;
        }
    }

    public int solution(int destId, Graph graph) {
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.depth, b.depth));
        pq.offer(new State(-1, -1, 1));

        boolean[] visited = new boolean[graph.maxNodeId + 1];
        while (!pq.isEmpty()) {
            State curr = pq.poll();
            if (curr.to == destId) return curr.depth + 1;

            for (int neighbor : graph.getNeighbors(curr.to)) {
                if (!visited[neighbor]) {
                    pq.offer(new State(curr.depth + 1, curr.to, neighbor));
                    visited[neighbor] = true;
                }
            }
        }

        return -1;
    }

    class State {
        int depth, from, to;

        public State(int depth, int from, int to) {
            this.depth = depth;
            this.from = from;
            this.to = to;
        }
    }
}