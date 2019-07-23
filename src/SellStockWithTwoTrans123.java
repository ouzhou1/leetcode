import java.util.Arrays;

/**
 * Description: 买卖股票，只能有两次的交易
 *
 * Solution: 用四个变量，分别记录两次交易买卖的最大利润，买的利润是-price，卖后+price
 *
 * @author peter
 * @date 2019/6/17.
 */
public class SellStockWithTwoTrans123 {

    public static int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        for (int price : prices) {
            if (buy1 < -price) {
                buy1 = -price;
            }
            if (sell1 < buy1 + price) {
                sell1 = buy1 + price;
            }
            if (buy2 < -price + sell1) {
                buy2 = sell1 - price;
            }
            if (sell2 < buy2 + price) {
                sell2 = buy2 + price;
            }
        }
        return sell2;
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        System.out.println(maxProfit(input));
    }
}
