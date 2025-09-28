import java.util.List;
import java.util.Map;

class Solution {

    final private double upperBoundRate = 1.05;

    int solution(Map<String, Integer> itemPrices, List<Transaction> transactions) {
        int exceedCount = 0;
        for (var entry : transactions) {
            int originalPrice = itemPrices.get(entry.itemName);
            double limit = originalPrice * upperBoundRate;

            if (entry.tradedPrice > limit) ++exceedCount;
        }
        return exceedCount;
    }
}