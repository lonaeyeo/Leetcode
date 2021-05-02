package DFSAndBFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class CharPermutation {
    /**
     * 剑指 Offer 38. 字符串的排列
     * <p>
     * dfs，记得剪枝
     */
    List<String> list;
    private char[] c;

    public String[] permutation(String s) {
        list = new LinkedList<>();
        c = s.toCharArray();
        dfs(0);
        return list.toArray(new String[c.length]);
    }

    private void dfs(int x) {
        // 退出条件
        if (x == c.length - 1)
            list.add(String.valueOf(c));

        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            // 判断重复字母
            if (set.contains(c[i])) continue;
            // 注意现在固定的是c[i]
            set.add(c[i]);
            // 将[i]固定在x这个位置；
            swap(i, x);
            dfs(x + 1);
            // 还原，因为最底可以还原，所以能保证从下往上时全都可以还原
            swap(i, x);
        }
    }

    private void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }
}
