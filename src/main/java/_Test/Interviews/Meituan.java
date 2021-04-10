package _Test.Interviews;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Meituan {


    public static int t2(int n, int m, int[] a, int[] b) {
        HashMap<Integer, Integer> mapB = new HashMap<>();
        for (int i = 0; i < b.length; i++) {
            mapB.put(b[i], mapB.getOrDefault(b[i], 0) + 1);
        }
        boolean flag = true;
        HashMap<Integer, Integer> mapA = new HashMap<>();

        // x的取值范围只能是[1, m-1]
        for (int x = 1; x < m; x++) {
            // 迭代转换A数组，并判断转换过的数字是否在mapB中存在
            for (int i = 0; i < a.length; i++) {
                int temp = (a[i] + x) % m;
                if (!mapB.containsKey(temp)) break;
                mapA.put(temp, mapA.getOrDefault(temp, 0) + 1);
            }
            // 这部分mapB和mapA的对比，size都不一样，直接计算下一个x
            if (mapA.size() == mapB.size()) {
                // 如果各项都一样，那么flag一直都是true
                for (Map.Entry<Integer, Integer> pair : mapB.entrySet()) {
                    if (!mapA.containsKey(pair.getKey()) || mapA.get(pair.getKey()) != pair.getValue()) {
                        flag = false;
                        break;
                    }
                }
            } else {
                flag = false;
            }
            mapA.clear();
            if (flag == false) {
                flag = true;
                continue;
            } else {
                return x;
            }
        }
        return 0;
    }


    @Test
    public void test() {
        System.out.println(Meituan.t2(6, 8, new int[]{1, 1, 4, 5, 1, 4}, new int[]{3, 0, 4, 0, 3, 0}));
    }


    @Test
    public void test3() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.pow(10, 9));
        System.out.println(Meituan.splitArray(5, 3, 4, new int[]{5, 8, 3, 10, 7}));
    }

    public static int splitArray(int n, int m, int C, int[] nums) {
        int[] times = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            times[i] = nums[i] / C;
            times[i] = nums[i] % C == 0 ? times[i] : times[i] + 1;
        }

        // dp
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + times[i];
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }

}
