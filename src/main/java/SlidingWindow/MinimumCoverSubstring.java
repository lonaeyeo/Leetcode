package SlidingWindow;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MinimumCoverSubstring {

    @Test
    public void test() {
        MinimumCoverSubstring minimumCoverSubstring = new MinimumCoverSubstring();
        System.out.println(minimumCoverSubstring.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(Integer.MAX_VALUE);
    }

    /**
     * 最小覆盖子串
     *
     * 128到127之间的数据放到了IntegerCache中，IntegerCache是static的，因此将会放到常量池中作为缓存使用
     * 因此可知，Integer a = 10;Integer b = 10;这两个对象其实是从IntegerCache缓存中取的，是同一个对象，地址肯定是相同的。
     * 而Integer a = 189;Integer b = 189;是创建的两个新的对象，因此地址肯定不同的。
     *
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char cKey = t.charAt(i);
            need.put(cKey, need.getOrDefault(cKey, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        int mark = 0;

        while (right < s.length()) {
            char rchar = s.charAt(right);
            right++;

            if (need.containsKey(rchar)) {
                window.put(rchar, window.getOrDefault(rchar, 0) + 1);
                // 注意.equals的用法
                if (need.get(rchar).equals(window.get(rchar)))
                    mark++;
            }

            // 当s的子串能够覆盖所有t时，窗口开始缩小
            while (mark == need.size()) {
                // 判断此子串是否为最短子串
                if (right - left < len) {
                    len = right - left;
                    start = left;
                }
                char lchar = s.charAt(left);
                left++;
                if (need.containsKey(lchar)) {
                    if (need.get(lchar).equals(window.get(lchar)))
                        mark--;
                    window.put(lchar, window.getOrDefault(lchar, 0) - 1);
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
