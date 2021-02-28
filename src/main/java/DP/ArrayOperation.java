package DP;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ArrayOperation {

    /**
     * 最长连续递增序列
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) return 0;

        int len = 1;
        int maxLen = 1;
        int prev = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > prev)
                ++len;
            else
                len = 1;
            prev = nums[i];
            maxLen = Math.max(len, maxLen);
        }
        return maxLen;
    }


    /**
     * 最长续增子序列（不连续）
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (nums.length == 0) return 0;
        // 以[i]结尾的最长递增子序列
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int ans = 1;

        for (int i = 1; i < len; i++) {
            // 暴力
            for (int j = 0; j < i; j++) {
                // 扫描之前每一个dp，发现比自己小的就+1，并和自己比较大
                // 所以一路都会是“当前最大”的状态
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }


    /**
     * 接雨水 （类似：最大矩形）
     * 给定一组高度，测算可接雨水大小。height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 首先，测定可接雨水涉及到池子两边高度，所以必定会涉及到left和right的值，
     * 即当前池子的高度 = rightMax - leftMax
     * <p>
     * 基本动态规划方法：牺牲空间记录每个池子的对应rightMax和leftMax
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len == 0) return 0;
        int[] leftMaxs = new int[len];
        int[] rightMaxs = new int[len];
        leftMaxs[0] = height[0];
        rightMaxs[len - 1] = height[len - 1];

        for (int i = 1; i < len; i++) {
            // 这个过程会不断传递其目前左侧的最高值
            leftMaxs[i] = Math.max(leftMaxs[i - 1], height[i]);
        }
        for (int i = len - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMaxs[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 1; i < len - 1; i++) {
            ans += Math.min(leftMaxs[i], rightMaxs[i]) - height[i];
        }
        return ans;
    }


    /**
     * 零钱兑换
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 初始化为amount+1是因为所需硬币数可能为amount
        // 不能置Max，因为会溢出
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = coins.length - 1; j >= 0; j--) {
                if (i - coins[j] < 0)
                    continue;
                // 如果dp[i-coins[j]]=amount+1的话，不会影响结果
                dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**
     * 礼物最大化，相当于最大路径
     */
    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + grid[i - 1][j - 1];
            }
        }

        return dp[m][n];
    }

    /**
     * 不同路径
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[0][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m][n];
    }


    /**
     * 三角形中最短路径
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[n - 1][i]);
        }

        return res;
    }

    // 压缩空间版本
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];

        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            // 表示左上的数值
            int upLeft = dp[0];
            int up = 0;
            dp[0] = dp[0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                up = dp[j];
                dp[j] = Math.min(upLeft, dp[j]) + triangle.get(i).get(j);
                upLeft = up;
            }
            dp[i] = upLeft + triangle.get(i).get(i);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[i]);
        }

        return res;
    }
}
