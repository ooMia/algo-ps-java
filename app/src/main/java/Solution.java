import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    public int solution(int N, int M, int[][] paths) {
        // from 기준으로 오름차순 정렬
        Arrays.sort(paths, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));
        System.err.println(Arrays.deepToString(paths));
        // depth 기준으로 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        // 방문 기록 배열 (출발점의 최댓값 길이)
        boolean[] visited = new boolean[paths[paths.length - 1][0] + 1];

        // 초기 상태 삽입
        // [0]: depth, [1]: from, [2]: to
        for (int i = 0; i < paths.length && paths[i][0] == 1; ++i) {
            pq.add(new int[]{0, paths[i][0], paths[i][1]});
        }
        visited[1] = true;

        // BFS 순회하며 상태 갱신
        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // [0]: depth, [1]: from, [2]: to
            final int depth = curr[0], to = curr[2];

            if (to == M) return depth + 1; // 종료 조건
            if (to >= visited.length) continue; // 방문할 수 없는 노드면 스킵
            if (visited[to]) continue; // 이미 방문했으면 스킵

            // 출발점 갱신
            final int from = to;
            visited[from] = true; // 방문 처리

            int iStart = _findMinIndex(paths, curr);
            System.err.println(Arrays.toString(curr));
            System.err.println("iStart: " + iStart);
            if (iStart < 0) continue; // 다음 노드를 출발점으로 하는 경로가 없으면 스킵
            for (int i = iStart; i < paths.length && paths[i][0] == from; ++i) {
                if (!visited[paths[i][1]])
                    pq.add(new int[]{depth + 1, paths[i][0], paths[i][1]});
            }
        }
        return -1;
    }

    private int _findMinIndex(int[][] paths, int[] targetState) {
        int target = targetState[2];
        int lo = 0, hi = paths.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1; // (lo + hi) / 2의 오버플로우 방지 버전
            if (paths[mid][0] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return (lo < paths.length && paths[lo][0] == target) ? lo : -1;
    }

}
