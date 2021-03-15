package SlidingWindow;

import org.junit.Test;

import java.util.*;

public class ConnectWordsSubstrings {

    @Test
    public void test() {
        ConnectWordsSubstrings c = new ConnectWordsSubstrings();
        System.out.println(c.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
    }


    /**
     * 串联所有单词的子串
     * 滑动窗口，窗口不是用16限制住，而是用根据wordsMap和currMap进行数据比较来窗口变化
     * 具体：
     * 1. 取一个词语，添加进currMap，判断是否属于wordsMap，如若不是，currMap重新初始化；
     * 2. 如若是，添加进currMap，判断currMap(s) > wordsMap(s)，是就窗口缩小；
     * 3. 循环1、2步，并进行count比较。
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordLen = words[0].length();
        int wordsLen = words.length * wordLen;
        if (wordsLen > s.length() || wordsLen == 0 || s.length() == 0)
            return res;

        // words对应的hashmap，通过wordmap和探测map对比，判断是否符合条件
        HashMap<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordMap.put(words[i], wordMap.getOrDefault(words[i], 0) + 1);
        }
        System.out.println(wordMap);

        // 探测时map，以及计数词count
        HashMap<String, Integer> currMap = new HashMap<>();
        int count = 0;
        // 按词长度启动窗口，
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;

            // 注意预知的right+wordLen是可以刚好等于s.length的，因为right+wordLen为新单词的首个index
            while (right + wordLen <= s.length()) {
                // 从s中获取一个词语
                String rS = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordMap.containsKey(rS)) {
                    currMap.put(rS, currMap.getOrDefault(rS, 0) + 1);
                    count++;
                    // 如果超量，得删除一些，可能会将原有OK的删除掉
                    // 所以总能窗口确保小于wordsLen
                    while (currMap.get(rS) > wordMap.get(rS)) {
                        String lS = s.substring(left, left + wordLen);
                        // 此步可能会将原有删除
                        if (currMap.get(lS) == 1) {
                            currMap.remove(lS);
                        } else {
                            currMap.put(lS, currMap.get(lS) - 1);
                        }
                        count--;
                        left += wordLen;
                    }
                } else {
                    // 如果wordMap本来就没有这个词，那么left将重置，窗口会初始化
                    count = 0;
                    currMap.clear();
                    left = right;
                }
                // while循环中，即可判断是否存在，因为可能满足条件的会有多串
                // 所以需多次比较大小
                if (count == words.length)
                    res.add(left);
            }
            // 每次for都要清算
            count = 0;
            currMap.clear();
        }
        return res;
    }
}
