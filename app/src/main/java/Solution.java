import java.util.Arrays;

class Solution {

    public int solution(int nVertices, int nEdges, int[][] edges) {
        Arrays.sort(edges, (e1, e2) -> Integer.compare(e1[2], e2[2]));
        int[] parent = new int[nVertices + 1];
        int nSelectedEdges = 0, totalWeight = 0;

        while (nSelectedEdges < nVertices - 1) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];

                if (find(parent, u) != find(parent, v)) {
                    union(parent, u, v);
                    nSelectedEdges++;
                    totalWeight += weight;
                }
            }
        }
        return totalWeight;
    }

    int find(int[] parent, int u) {
        if (parent[u] == 0) {
            return u;
        }
        if (parent[u] != u) {
            parent[u] = find(parent, parent[u]);
        }
        return parent[u];
    }

    void union(int[] parent, int u, int v) {
        int rootU = find(parent, u);
        int rootV = find(parent, v);
        if (rootU != rootV) {
            parent[rootV] = rootU;
        }
    }
}