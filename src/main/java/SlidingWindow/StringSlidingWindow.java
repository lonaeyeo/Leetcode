package SlidingWindow;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StringSlidingWindow {

    @Test
    public void test() {
        StringSlidingWindow stringSlidingWindow = new StringSlidingWindow();
        System.out.println(stringSlidingWindow.checkInclusion("ab", "eidboaoo"));
    }

    /**
     * 字符串的排列
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            char temp = s1.charAt(i);
            need.put(temp, need.getOrDefault(temp, 0) + 1);
        }


        int valid = 0;

        int left = 0;
        int right = 0;

        while (right < s2.length()) {
            char r = s2.charAt(right);
            right++;

            if (need.containsKey(r)) {
                window.put(r, window.getOrDefault(r, 0) + 1);
                if (need.get(r).intValue() == window.get(r).intValue())
                    valid++;
            }

            // 如果滑动窗口的大小 >= s1的长度，开始缩小
            while (right - left >= s1.length()) {
                if (valid == need.size())
                    return true;

                char l = s2.charAt(left);
                left++;

                if (need.containsKey(l)) {
                    if (need.get(l).intValue() == window.get(l).intValue())
                        valid--;
                    window.put(l, window.getOrDefault(l, 0) - 1);
                }
            }
        }

        return false;
    }
}
