package DP;

import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.*;

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


    /**
     * 乘积最大子数组
     */
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        // 以[i]结尾的最大正数和
        int[] dpP = new int[len];
        // 最小负数（最小负数乘以另一个负数 得到 最大整数）
        int[] dpN = new int[len];
        if (nums[0] > 0) {
            dpP[0] = nums[0];
            dpN[0] = 0;
        } else {
            dpP[0] = 0;
            dpN[0] = nums[0];
        }

        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                dpP[i] = dpP[i - 1] == 0 ? nums[i] : dpP[i - 1] * nums[i];
                dpN[i] = nums[i] * dpN[i - 1];
            } else {
                dpP[i] = nums[i] * dpN[i - 1];
                dpN[i] = dpP[i - 1] == 0 ? nums[i] : dpP[i - 1] * nums[i];
            }
        }

        int res = 0;
        for (int dp : dpP) {
            res = Math.max(res, dp);
        }
        return res;
    }


    /**
     * 拆分整数
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        // 2起步
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                // 二分或者，多分
                max = Math.max(max, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = max;
        }

        return dp[n];
    }


    /**
     * 回文子串
     * 看s中有多少个子串
     * dp：以[i][j]的子串是否为回文子串
     */
    public int countSubstrings(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = true;
            }
        }

        int res = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                res = dp[i][j] ? res + 1 : res;
            }
        }

        return res + len;
    }


    /**
     * 不同路径2：有障碍物
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                int temp = j - 1 >= 0 ? dp[j - 1] : 0;
                dp[j] = dp[j] + temp;
            }
        }
        return dp[n - 1];
    }

    /**
     * 无重复区间
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[1];
            }
        });

        // 以intervals[i]结尾的最大无重复区间数
        int[] dp = new int[intervals.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]) {
                    // dp[j]都是从dp[j-1]上来的
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 贪心
    public int eraseOverlapIntervals1(int[][] intervals) {
        if (intervals.length == 0) return 0;

        // 按[, 1][, 2][, 3]排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int res = 1;
        int right = intervals[0][1];

        // 只需比较 右界和 新的左界 做对比
        for (int i = 1; i < intervals.length; i++) {
            if (right <= intervals[i][0]) {
                res++;
                right = intervals[i][1];
            }
        }
        return intervals.length - res;
    }


    /**
     * 剑指 Offer 46. 把数字翻译成字符串
     * [0,...,25] --> [a,...,z]
     */
    public int translateNum(int num) {
        if (num < 10)
            return 1;
        // 转换成String串来处理，就不需要新建一个list
        String str = new Integer(num).toString();
        int[] dp = new int[str.length()];
        dp[0] = 1;
        if ((str.charAt(0) - '0') * 10 + (str.charAt(1) - '0') < 26) dp[1] = dp[0] + 1;
        else dp[1] = dp[0];

        for (int i = 2; i < str.length(); i++) {
            int curr = str.charAt(i) - '0';
            int pre = str.charAt(i - 1) - '0';
            if (pre == 0)
                dp[i] = dp[i - 1];
            else
                dp[i] = dp[i - 1] + (curr + pre * 10 < 26 ? dp[i - 2] : 0);
        }

        return dp[str.length() - 1];
    }


    @Test
    public void test() {
        ArrayOperation a = new ArrayOperation();
        System.out.println(a.translateNum(542));
//        System.out.println(new Integer(27873).toString());
//        System.out.println('9'-0);
    }

}
