/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/6/17.
 */
public class SellStockWithMultiTransaction122 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int buy = prices[0], sell = prices[1], profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                sell = prices[i];
            } else if (prices[i] < prices[i - 1]) {
                if (sell > buy) {
                    profit += (sell - buy);
                }
                buy = sell = prices[i];
            }
        }
        return profit + (sell > buy ? (sell - buy) : 0);
    }
}
