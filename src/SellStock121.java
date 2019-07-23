/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/6/17.
 */
public class SellStock121 {

    public static int maxProfit(int[] prices) {
        int buy = prices[0], profit = 0;
        for (int price : prices) {
            if (price < buy) {
                buy = price;
            } else if (price > buy) {
                profit = Math.max(profit, price - buy);
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }
}
