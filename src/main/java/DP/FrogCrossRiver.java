package DP;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FrogCrossRiver {

    public boolean canCross(int[] stones) {
        int n = stones.length;
        Map<Integer, Set<Integer>> dp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            dp.put(stones[i], new HashSet<Integer>());
        }
        dp.get(stones[0]).add(0);

        for (int i = 0; i < n; i++) {
            for (int K : dp.get(stones[i])) {
                for (int currK = K - 1; currK <= K + 1; currK++) {
                    // currK不为0，并且hashmap包含这个key
                    if (currK > 0 && dp.containsKey(currK + stones[i]))
                        dp.get(currK + stones[i]).add(currK);
                }

            }
        }

        return dp.get(stones[n - 1]).size() > 0;
    }

    @Test
    public void test() {
        System.out.format("%s|%s", 1, 2, 3);
    }

}
