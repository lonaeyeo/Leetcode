package DP;

import org.junit.Test;

public class GasStation {

    @Test
    public void test() {
        GasStation gasStation = new GasStation();
        int[] gas = new int[]{2, 3, 4};
        int[] cost = new int[]{3, 4, 3};
        System.out.println(gasStation.canCompleteCircuit1(gas, cost));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // dp用来记录每个加油站作为起点 可能的累计结果
        for (int i = 0; i < gas.length; ++i) {
            int dp = 0;
            int next = i;
            do {
                dp = dp + gas[next] - cost[next];
                if (dp < 0) {
                    break;
                }
                next = (next + 1) % gas.length;
            } while (next != i);
            // 如果出现dp<0，表示此路不通
            // 第一次迭代（即next==i），且dp>=0的情况，不会出现在下述判断句
            if (next == i && dp >= 0)
                return i;
        }
        return -1;
    }

    public int canCompleteCircuit1(int[] gas, int[] cost) {
        // 记录每个加油站i作为起始站的耗油初始情况
        int dp[] = new int[gas.length];
        for (int i = 0; i < dp.length; ++i) {
            dp[i] = gas[i] - cost[i];
        }

        for (int i = 0; i < dp.length; ++i) {
            if (dp[i] < 0) {
                continue;
            } else {
                int sum = dp[i];
                int next = (i + 1) % dp.length;
                while (next != i) {
                    sum = sum + dp[next];
                    if (sum < 0)
                        break;
                    next = (next + 1) % dp.length;
                }
                // 如果出现dp<0，表示此路不通
                // 第一次迭代（即next==i），且dp>=0的情况，不会出现在下述判断句
                if (next == i && sum >= 0)
                    return i;
            }
        }

        return -1;
    }
}
