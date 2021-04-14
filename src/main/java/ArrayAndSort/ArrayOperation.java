package ArrayAndSort;

import com.sun.corba.se.impl.oa.poa.AOMEntry;
import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import java.util.*;

public class ArrayOperation {

    @Test
    public void test() {
        ArrayOperation arrayOperation = new ArrayOperation();
        int[] a = new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] b = new int[]{2, 1, 4, 3, 9, 6};

        a = arrayOperation.relativeSortArray(a, b);
        for (int t : a) {
            System.out.println(t);
        }
    }

    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new TreeMap<>();

        for (int a1num : arr1) {
            int num = map.getOrDefault(a1num, 0) + 1;
            map.put(a1num, num);
        }

        int index = 0;
        for (int a2num : arr2) {
            for (int i = 0; i < map.get(a2num); ++i) {
                arr1[index] = a2num;
                ++index;
            }
            map.remove(a2num);
        }

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Integer key = (Integer) entry.getKey();
            Integer numOfKey = (Integer) entry.getValue();
            for (int i = 0; i < numOfKey; ++i) {
                arr1[index] = key;
                ++index;
            }
        }

        return arr1;
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        // int[]数组不能用Arrays.sort(, Comparator...)
        // 必须要Integer[]数组
        // 所以这里用一个ArrayList
        List<Integer> list = new ArrayList<>();
        for (int num : arr1) list.add(num);
        for (int i = 0; i < arr2.length; i++) map.put(arr2[i], i);
        Collections.sort(list, (x, y) -> {
            if (map.containsKey(x) || map.containsKey(y)) return map.getOrDefault(x, 1001) - map.getOrDefault(y, 1001);
            return x - y;
        });
        for (int i = 0; i < arr1.length; i++) arr1[i] = list.get(i);
        return arr1;
    }

    @Test
    public void test1() {
        String s = "ass";
        String b = "ssa";
        System.out.println(ArrayOperation.isAnagram(s, b));
    }

    public static boolean isAnagram(String s, String t) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        if (s.length() != t.length())
            return false;

        for (int i = 0; i < s.length(); i++) {
            int flag = s.charAt(i) - 'a';
            count1[flag]++;
            flag = t.charAt(i) - 'a';
            count2[flag]++;
        }

        for (int j = 0; j < 26; j++) {
            if (count1[j] != count2[j])
                return false;
        }

        return true;
    }

    @Test
    public void test2() {
        System.out.println(ArrayOperation.sortString("leetcode"));

    }

    public static String sortString(String s) {
        // 用26位数组来装
        int[] nums = new int[26];

        for (int i = 0; i < s.length(); ++i) {
            ++nums[s.charAt(i) - 'a'];
        }

        StringBuffer ans = new StringBuffer();

        while (ans.length() < s.length()) {
            for (int i = 0; i < 26; ++i) {
                if (nums[i] != 0) {
                    --nums[i];
                    ans.append((char) (i + 'a'));
                }
            }
            for (int i = 25; i >= 0; --i) {
                if (nums[i] != 0) {
                    --nums[i];
                    ans.append((char) (i + 'a'));
                }
            }
        }
        return ans.toString();
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<>();
        Map<Integer, Integer> countCD = new HashMap<>();
        int ans = 0;

        for (int a : A)
            for (int b : B) {
                countAB.put(a + b, 1 + countAB.getOrDefault(a + b, 0));
            }

        for (int c : C)
            for (int d : D) {
                if (countAB.containsKey(-c - d)) {
                    ans += countAB.get(-c - d);
                }
            }
        return ans;
    }

    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i > 1; --i) {
            if (A[i] < A[i - 1] + A[i - 2])
                return A[i] + A[i - 1] + A[i - 2];
        }
        return 0;
    }

    // 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> currRow = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    currRow.add(1);
                else
                    currRow.add(results.get(i - 1).get(j - 1) + results.get(i - 1).get(j));
            }
            results.add(currRow);
        }
        return results;
    }

    // 剪绳子
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3;
        int b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        else if (b == 1) return (int) Math.pow(3, a - 1) * 4;
        else return (int) Math.pow(3, a) * 2;
    }

    /**
     * 最小路径
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][1] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }

        return dp[m][n];
    }

    @Test
    public void test3() {
        System.out.println(178 & 1);
        System.out.println(-1 >>> 1);
        ArrayOperation a = new ArrayOperation();
//        System.out.println(a.minPathSum(new int[][]{{1, 2}, {1, 1}}));
        int b = -1;
        System.out.println(Integer.toBinaryString(b));
        System.out.println(a.reverseBits(b));
    }


    /**
     * num中1111最大
     */
    public int reverseBits(int num) {
        int n = num;
        if (n == 0) return 1;
        if (n == -1) return 32;

        int curr = 0;
        int res = 0;
        int pre = 0;

        while (n != 0) {
            if ((n & 1) == 1) {
                curr++;
            } else {
                // 从一个方向开始，只需考虑前面一个0
                // pre可能是一个0以前的连续1串，也可能是不是
                res = Math.max(res, curr + pre + 1);
                // 第一次遇0时，pre>0，再次遇0时，pre=0
                pre = curr;
                curr = 0;
            }
            // ">>"有符号右移，">>>"无符号右移
            n = n >>> 1;
        }
        res = Math.max(res, curr + pre + 1);
        return res;
    }


}
