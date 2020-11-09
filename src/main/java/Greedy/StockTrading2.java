package Greedy;

import org.junit.Test;

public class StockTrading2 {

    @Test
    public void test() {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(StockTrading2.maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int sum = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < prices.length; ++i) {
            end = i;
            if (prices[start] < prices[end]) {
                sum = sum + (prices[end] - prices[start]);
            }
            start = i;
        }
        return sum;
    }
}
