import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N, M; // N: 물품의 개수, M: 거래 내역의 개수
    final Map<String, Integer> itemPrices;
    final List<Transaction> transactions;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var line = reader.readInts();
            N = line[0];
            M = line[1];

            itemPrices = new java.util.HashMap<>(N);
            for (int n = 0; n < N; ++n) {
                var item = br.readLine().split(" ");
                itemPrices.put(item[0], Integer.parseInt(item[1]));
            }

            transactions = new java.util.ArrayList<>(M);
            for (int m = 0; m < M; ++m) {
                var transaction = br.readLine().split(" ");
                transactions.add(new Transaction(transaction[0], Integer.parseInt(transaction[1])));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write('\n');
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution();
        var res = sol.solution(itemPrices, transactions);
        _write(res);
    }

    private void _write(Object o) {
        try {
            bw.write(String.valueOf(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}