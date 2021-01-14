package SlidingWindow;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StringSlidingWindow {

    @Test
    public void test() {
        StringSlidingWindow stringSlidingWindow = new StringSlidingWindow();
//        System.out.println(stringSlidingWindow.checkInclusion("ab", "eidboaoo"));
        System.out.println(stringSlidingWindow.findAnagrams("baa", "aa"));
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


    /**
     * 找到字符串中所有字母异位词
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     */
    public List<Integer> findAnagrams(String s, String p) {
        int[] need = new int[26];
        int[] window = new int[26];
        // 字母种类的数量
        int needSize = 0;

        for (int i = 0; i < p.length(); i++) {
            ++need[p.charAt(i) - 'a'];
        }
        for (int i = 0; i < need.length; i++) {
            needSize = need[i] == 0 ? needSize : needSize + 1;
        }

        List<Integer> ans = new LinkedList<>();
        int left = 0;
        int right = 0;
        int valid = 0;

        while (right < s.length()) {
            char r = s.charAt(right);
            right++;

            if (need[r - 'a'] != 0) {
                window[r - 'a']++;
                if (need[r - 'a'] == window[r - 'a'])
                    valid++;
            }

            // 如果滑动窗口的大小 >= p的长度，开始缩小
            while (right - left >= p.length()) {
                // 如果valid达到needSize，ans添加一个子串的left值
                if (valid == needSize)
                    ans.add(left);
                char l = s.charAt(left);
                left++;

                if (need[l - 'a'] != 0) {
                    if (need[l - 'a'] == window[l - 'a'])
                        valid--;
                    window[l - 'a']--;
                }
            }
        }

        return ans;
    }


    /**
     * 剑指 Offer 48. 最长不含重复字符的子字符串
     *
     * @return 子串长度
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int start = 0;
        int maxLen = 0;

        // 当前最长不重复子串的 长度 如果超过剩余未扫描的字符串长度，就没有必要继续往前移动窗口了
        while (right < s.length() && maxLen <= s.length() - left) {
            char r = s.charAt(right);
            right++;

            window.put(r, window.getOrDefault(r, 0) + 1);

            // 如果遇到重复的字符，则窗口开始收缩，直到重复字符不再重复
            while (window.get(r) > 1) {

                char l = s.charAt(left);
                left++;

                window.put(l, window.getOrDefault(l, 0) - 1);
            }

            // 判断是否为最长不重复子串
            if (right - left > maxLen) {
                start = left;
                maxLen = right - left;
            }
        }

        return maxLen;
    }
}
