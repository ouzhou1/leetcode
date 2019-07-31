/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/6/18.
 */
public class SellStockWithCooldown309 {

    public static int maxProfit(int[] prices) {
        int sell = 0, prevSell = 0, buy = Integer.MIN_VALUE, prevBuy;
        for (int price : prices) {
            prevBuy = buy;
            buy = Math.max(prevSell - price, prevBuy);
            prevSell = sell;
            sell = Math.max(prevBuy + price, prevSell);
        }
        return sell;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 0, 2};
        System.out.println(maxProfit(input));
    }
}
