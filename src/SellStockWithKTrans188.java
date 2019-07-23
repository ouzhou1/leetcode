/**
 * Description: K次股票交易最大利润
 *
 * Solution: 在两次交易的基础上拓展，用dp数组记录中间交易
 *
 * @author peter
 * @date 2019/6/17.
 */
public class SellStockWithKTrans188 {

    public static int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) {
            return quickSolve(prices);
        }

        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int buyMax = -prices[0];
            for (int j = 1; j < len; j++) {
                t[i][j] = Math.max(t[i][j - 1], prices[j] + buyMax);
                buyMax = Math.max(buyMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][len - 1];
    }


    private static int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
