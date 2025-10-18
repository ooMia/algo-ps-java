import java.util.Arrays;

class Solution {

    static class Graph {
        final int maxNodeId; // = destId
        final int[] rowPtr;  // 각 정점의 이웃 시작 오프셋 (head)
        final int[] colIdx;  // 인접 정점 id를 일렬 저장 (edges)

        Graph(int destId, int[][] paths) {
            this.maxNodeId = destId; // 문제 입력에서 N과 동일하다는 가정

            // 각 정점의 출력 차수(deg) 계산
            int[] outDegree = new int[maxNodeId + 1];
            for (int[] e : paths) {
                int from = e[0];
                ++outDegree[from];
            }

            // prefix sum으로 rowPtr 구성 (u의 이웃 구간: [rowPtr[u], rowPtr[u+1]))
            this.rowPtr = new int[maxNodeId + 2];
            for (int node = 1; node <= maxNodeId; node++) {
                rowPtr[node + 1] = rowPtr[node] + outDegree[node];
            }

            // colIdx(edges) 채우기, writePos는 쓰기 위치 커서
            this.colIdx = new int[rowPtr[maxNodeId + 1]];
            int[] writePos = Arrays.copyOf(rowPtr, rowPtr.length);
            for (int[] e : paths) {
                int from = e[0], to = e[1];
                colIdx[writePos[from]++] = to;
            }
        }
    }

    // 1에서 destId까지의 최소 변화 횟수. 불가능하면 -1
    public int solution(int destId, Graph g) {
        if (destId == 1) return 0;
        if (g == null || g.maxNodeId < 1) return -1;

        int maxNodeId = g.maxNodeId;
        int[] distance = new int[maxNodeId + 1];
        Arrays.fill(distance, -1); 

        // 고정 배열 큐(원형 큐)
        int[] queue = new int[Math.max(2, maxNodeId + 1)];
        int qHead = 0, qTail = 0;

        distance[1] = 0;
        queue[qTail++] = 1;

        while (qHead < qTail) {
            int uId = queue[qHead++];
            if (uId == destId) return distance[uId];

            // CSR 범위: [rowPtr[uId], rowPtr[uId+1])
            int start = g.rowPtr[uId];
            int end = g.rowPtr[uId + 1];
            for (int idx = start; idx < end; idx++) {
                int vId = g.colIdx[idx];
                if (distance[vId] == -1) {
                    distance[vId] = distance[uId] + 1;
                    if (vId == destId) return distance[vId];
                    queue[qTail++] = vId;
                }
            }
        }
        return -1;
    }
}