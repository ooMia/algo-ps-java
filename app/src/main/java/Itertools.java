
import java.util.ArrayList;
import java.util.List;

class Itertools {
    public static List<int[]> permutations(int[] numbers, int r) {
        List<int[]> resultList = new ArrayList<>();
        int n = numbers.length;

        if (r > n || r < 0) {
            return resultList;
        }

        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        int[] cycles = new int[r];
        for (int i = 0; i < r; i++) {
            cycles[i] = n - i;
        }

        // 첫 번째 순열 추가
        int[] firstPermutation = new int[r];
        for (int i = 0; i < r; i++) {
            firstPermutation[i] = numbers[indices[i]];
        }
        resultList.add(firstPermutation);

        while (true) {
            boolean broken = false;
            for (int i = r - 1; i >= 0; i--) {
                cycles[i]--;
                if (cycles[i] == 0) {
                    // 인덱스 배열을 오른쪽으로 한 칸 회전
                    int temp = indices[i];
                    for (int j = i; j < n - 1; j++) {
                        indices[j] = indices[j + 1];
                    }
                    indices[n - 1] = temp;
                    cycles[i] = n - i;
                } else {
                    int j = cycles[i];
                    int swapIndex = n - j;

                    // 인덱스 교환
                    int temp = indices[i];
                    indices[i] = indices[swapIndex];
                    indices[swapIndex] = temp;

                    // 새 순열 추가
                    int[] newPermutation = new int[r];
                    for (int k = 0; k < r; k++) {
                        newPermutation[k] = numbers[indices[k]];
                    }
                    resultList.add(newPermutation);
                    broken = true;
                    break; // 안쪽 for 루프 탈출
                }
            }

            if (!broken) {
                // 안쪽 for 루프가 break 없이 완료되면 모든 순열을 찾은 것
                return resultList;
            }
        }
    }

    public static List<int[]> combinations(int[] numbers, int r) {
        List<int[]> resultList = new ArrayList<>();
        int n = numbers.length;

        if (r > n || r < 0) {
            return resultList;
        }

        int[] indices = new int[r];
        for (int i = 0; i < r; i++) {
            indices[i] = i;
        }

        while (true) {
            // 현재 조합 추가
            int[] currentCombination = new int[r];
            for (int i = 0; i < r; i++) {
                currentCombination[i] = numbers[indices[i]];
            }
            resultList.add(currentCombination);

            // 다음 조합으로 이동
            int i;
            for (i = r - 1; i >= 0 && indices[i] == n - r + i; i--)
                ;
            if (i < 0) {
                break; // 모든 조합을 생성했으면 종료
            }
            indices[i]++;
            for (int j = i + 1; j < r; j++) {
                indices[j] = indices[j - 1] + 1;
            }
        }

        return resultList;
    }

    public static List<int[]> combinationsWithRepetition(int[] numbers, int r) {
        List<int[]> resultList = new ArrayList<>();
        int n = numbers.length;

        if (r < 0) {
            return resultList;
        }

        int[] indices = new int[r];
        for (int i = 0; i < r; i++) {
            indices[i] = 0;
        }

        while (true) {
            // 현재 조합 추가
            int[] currentCombination = new int[r];
            for (int i = 0; i < r; i++) {
                currentCombination[i] = numbers[indices[i]];
            }
            resultList.add(currentCombination);

            // 다음 조합으로 이동
            int i;
            for (i = r - 1; i >= 0 && indices[i] == n - 1; i--)
                ;
            if (i < 0) {
                break; // 모든 조합을 생성했으면 종료
            }
            indices[i]++;
            for (int j = i + 1; j < r; j++) {
                indices[j] = indices[j - 1];
            }
        }

        return resultList;
    }

    public static List<int[]> product(int[] numbers, int r) {
        List<int[]> resultList = new ArrayList<>();
        if (r < 0) {
            return resultList;
        }
        if (r == 0) {
            resultList.add(new int[0]);
            return resultList;
        }

        int n = numbers.length;
        if (n == 0 && r > 0) {
            return resultList;
        }

        List<int[]> tempResult = new ArrayList<>();
        tempResult.add(new int[0]);

        for (int i = 0; i < r; i++) {
            List<int[]> nextResult = new ArrayList<>();
            for (int[] prev : tempResult) {
                for (int num : numbers) {
                    int[] newProduct = new int[prev.length + 1];
                    System.arraycopy(prev, 0, newProduct, 0, prev.length);
                    newProduct[prev.length] = num;
                    nextResult.add(newProduct);
                }
            }
            tempResult = nextResult;
        }

        resultList = tempResult;
        return resultList;
    }
}