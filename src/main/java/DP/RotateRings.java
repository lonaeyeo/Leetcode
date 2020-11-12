package DP;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RotateRings {

    @Test
    public void test() {
        RotateRings rotateRings = new RotateRings();
        int steps = rotateRings.findRotateSteps("godding", "dog");
        System.out.println(steps);
//        System.out.println(0x3f3f3f);
//        Integer[] test = new Integer[]{1,2,3,4,5,null};
//        test3[0] += 1;
//        System.out.println(test3[0]);
//        int[] test2 = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
//        System.out.println(Arrays.stream(test).min((o1,o2)->o1-o2));
//        System.out.println(Arrays.stream(test3).min());
    }

    public int findRotateSteps(String ring, String key) {
        int r = ring.length(), k = key.length();
        List<Integer>[] pos = new List[26];
        int[][] dp = new int[k][r];

        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<>();
        }

        // pos存储ring中每个字符的所有位置
        for (int i = 0; i < r; ++i) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        for (int i = 0; i < k; ++i) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }

        for (int num : pos[key.charAt(0) - 'a']) {
            dp[0][num] = 1 + Math.min(num, r - num);
        }

        for (int i = 1; i < k; ++i) {
            for (int num : pos[key.charAt(i) - 'a']) {
                // 读取上个字母 对应的各种可能 对当前字母对应的选项的 steps
                for (int knum : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][num] = Math.min(dp[i][num],
                            dp[i - 1][knum] + 1 + Math.min(Math.abs(i - knum), r - Math.abs(i - knum)));
                }
            }
        }

        return Arrays.stream(dp[k - 1]).min().getAsInt();
    }

    public int[] sortArrayByParityII(int[] A) {
        int[] even = new int[A.length / 2];
        int[] odd = new int[A.length / 2];

        for (int i = 0, ie = 0, io = 0; i < A.length; ++i) {
            if (A[i] % 2 == 0) {
                even[ie] = A[i];
                ++ie;
            } else {
                odd[io] = A[i];
                ++io;
            }
        }

        Arrays.sort(even);
        Arrays.sort(odd);

        for (int i = 0, ie = 0, io = 0; i < A.length; ++i) {
            if (i % 2 == 0) {
                A[i] = even[ie];
                ++ie;
            } else {
                A[i] = odd[io];
                ++io;
            }
        }

        return A;
    }
}
