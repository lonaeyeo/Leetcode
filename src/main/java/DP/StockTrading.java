package DP;

import org.junit.Test;

import java.util.Arrays;

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


    /**
     * 可进行次买卖
     * buy[i][j]: 交易完成j次，并包括新买入一次
     * sell[i][j]: 交易完成j次
     * 为什么两个二维数组呢：因为有两个动作，多次交易
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        // trick
        k = Math.min(k, prices.length + 1 / 2);
        int[][] buy = new int[prices.length][k + 1];
        int[][] sell = new int[prices.length][k + 1];
        Arrays.fill(buy[0], -prices[0]);
        // 预处理：对于buy[i][0]，都是没有完成过一次交易，都是首次买入，买的最低的即使入口
        for (int i = 1; i < prices.length; i++) {
            buy[i][0] = Math.max(buy[i - 1][0], -prices[i]);
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                // 要么继承buy[i-1][j]什么都不干，要么在sell的基础上新购入，“-”是表示新购入
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                //
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }

        return sell[prices.length - 1][k];
    }

    @Test
    public void test() {
        StockTrading s = new StockTrading();
        System.out.println(s.maxProfit(2, new int[]{2, 4, 1}));
    }

}
