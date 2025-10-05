
class Solution {
    final int BASKET_CAPACITY = 5;
    final int[] counts;
    int res;

    Solution(int[] counts) {
        this.counts = counts;
    }

    public int solution() {
        int iLast = BASKET_CAPACITY - 1; // 가능한 최대 무게 인덱스부터 시작
        res = counts[iLast];
        counts[iLast] = 0;

        for (int i = iLast - 1; i >= 0; --i) {
            if (counts[i] < 1) continue;

            int iWeight = i + 1;
            {
                // 이론상 최대 케이스
                // 예를 들어, 용량 5 바구니에 2kg 아이템은 2개씩 담음
                int nPerBasket = BASKET_CAPACITY / iWeight;
                int nFullBasket = counts[i] / nPerBasket;
                res += nFullBasket;
                counts[i] %= nPerBasket;

                // 최대 케이스 바구니의 나머지 빈 공간 채우기
                int capacity = BASKET_CAPACITY % iWeight;
                for (int j = capacity - 1; j >= 0 && nFullBasket > 0; --j) {
                    if (counts[j] < 1) continue;
                    int jWeight = j + 1;
                    int perBasketIdealN = capacity / jWeight;

                    // 현재 아이템 개수로 채울 수 있을만큼만 채움
                    int actualN = Math.min(counts[j], perBasketIdealN * nFullBasket);
                    counts[j] -= actualN;
                    nFullBasket -= actualN / perBasketIdealN;
                }
            }
            {
                if (counts[i] == 0) continue;

                res += 1; // 최대로 담은 이후, 잉여는 한 바구니로 처리
                int capacity = BASKET_CAPACITY - iWeight * counts[i];

                for (int j = i - 1; j >= 0 && capacity > 0; --j) {
                    if (j < 0 || counts[j] < 1) continue;
                    int jWeight = j + 1;
                    int idealN = capacity / jWeight;

                    // 현재 아이템 개수로 채울 수 있을만큼만 채움
                    int actualN = Math.min(counts[j], idealN);
                    counts[j] -= actualN;
                    capacity -= actualN * jWeight;
                }
            }
        }
        return res;
    }
}
