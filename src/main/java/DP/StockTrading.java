package DP;

public class StockTrading {

    /**
     * 股票买卖1
     * 一天买入，另一天卖出
     */
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int max = 0;

        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        return max;
    }


    /**
     * 可进行最多两次 获利操作
     * 新的买入之前，旧的必须卖出
     */
    public int maxProfit2(int[] prices) {
        int m = prices.length;
        // 第一次买或者没有操作
        int buy1 = -prices[0];
        // 第二次买或者没有操作
        int buy2 = -prices[0];
        int sell1 = 0;
        int sell2 = 0;

        for (int i = 1; i < m; i++) {
            // 逢低买进
            buy1 = Math.max(buy1, -prices[i]);
            // 计算逢高卖出
            sell1 = Math.max(sell1, prices[i] + buy1);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
