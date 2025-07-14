import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.TreeMap;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M;
    final int[] numbers;
    final NavigableMap<Data, Integer> history = new TreeMap<>();

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            var input = reader.readInts();
            N = input[0];
            M = input[1];
            sb.ensureCapacity(M * 2);
            numbers = reader.readInts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() throws IOException {
        for (int i = 0; i < M; ++i) {
            var inputs = reader.readInts();
            int iFrom = inputs[0] - 1, iTo = inputs[1] - 1;

            var data = new Data(iFrom, iTo);

            // 정확히 같은 구간이 있는지 먼저 확인
            if (history.containsKey(data)) {
                int cachedSum = history.get(data);
                sb.append(cachedSum).append('\n');
                continue;
            }

            // 재사용 가능한 구간 찾기
            Data bestKey = findBestKey(data);
            if (bestKey != null) {
                // System.err.println("Reuse advantage found: " + bestKey + " for " + data);
                int sum = reuse(iFrom, iTo, bestKey);
                sb.append(sum).append('\n');
                history.put(data, sum);
                continue;
            }

            // 재사용 가능한 구간이 없으면 직접 계산
            int sum = 0;
            for (int j = iFrom; j <= iTo; ++j) {
                sum += numbers[j];
            }
            sb.append(sum).append('\n');
            history.put(data, sum);
        }
    }

    private Data findBestKey(Data target) {
        if (history.isEmpty()) {
            return null;
        }

        Data keyA = history.floorKey(target);
        int scoreA = keyA != null ? keyA.evaluateReuseAdvantage(target) : -1;

        Data keyB = history.ceilingKey(target);
        int scoreB = keyB != null ? keyB.evaluateReuseAdvantage(target) : -1;

        int maxScore = Math.max(scoreA, scoreB);
        Data bestKey = scoreA >= scoreB ? keyA : keyB;
        return maxScore > 0 ? bestKey : null;
    }

    private int reuse(int iFrom, int iTo, Data ref) {
        boolean isCurrentContainsRef = iFrom <= ref.iFrom && ref.iTo <= iTo;
        int sum = history.get(ref);
        if (isCurrentContainsRef) {
            for (int i = iFrom; i < ref.iFrom; ++i) {
                sum += numbers[i];
            }
            for (int i = ref.iTo + 1; i <= iTo; ++i) {
                sum += numbers[i];
            }
        } else {
            for (int i = ref.iFrom; i < iFrom; ++i) {
                sum -= numbers[i];
            }
            for (int i = iTo + 1; i <= ref.iTo; ++i) {
                sum -= numbers[i];
            }
        }
        return sum;
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}

class Data implements Comparable<Data> {
    final int iFrom;
    final int iTo;

    Data(int iFrom, int iTo) {
        this.iFrom = iFrom;
        this.iTo = iTo;
    }

    @Override
    public int compareTo(Data o) {
        return Comparator.comparing((Data d) -> d.iFrom)
                .thenComparing(d -> d.iTo)
                .compare(this, o);
    }

    public int evaluateReuseAdvantage(Data target) {

        // this가 target을 포함하는 경우
        if (this.iFrom <= target.iFrom && target.iTo <= this.iTo) {
            int directCost = target.iTo - target.iFrom + 1;
            int reuseCost = (target.iFrom - this.iFrom) + (this.iTo - target.iTo);
            return directCost - reuseCost;
        }

        // target이 this를 포함하는 경우
        if (target.iFrom <= this.iFrom && this.iTo <= target.iTo) {
            int directCost = target.iTo - target.iFrom + 1;
            int reuseCost = (this.iFrom - target.iFrom) + (target.iTo - this.iTo);
            return directCost - reuseCost;
        }

        // 포함 관계가 아닌 경우
        return -1;
    }
}