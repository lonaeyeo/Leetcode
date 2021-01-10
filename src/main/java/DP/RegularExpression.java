package DP;

public class RegularExpression {

    /**
     * 正则表达式，'.' 匹配任意单个字符，'*' 匹配零个或多个前面的那一个元素
     * fp[i][j]表示前i、j个，但是对应在字符串的坐标为i-1,j-1.
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] fp = new boolean[m + 1][n + 1];
        // 0表示空字符串；1表示有一个字符，对应在字符串中的位置为“0”
        fp[0][0] = true;

        // [*][0]都是false
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    // 如果s的第i个和p的第j-1个相同
                    if (match(s, p, i, j - 1))
                        // 此处保留fp[i][j - 2]是因为存在"ba","baa*"这种情况
                        fp[i][j] = fp[i - 1][j] || fp[i][j - 2];
                    else
                        fp[i][j] = fp[i][j - 2];
                } else {
                    if (match(s, p, i, j))
                        fp[i][j] = fp[i - 1][j - 1];
                }
            }
        }
        return fp[m][n];
    }

    private boolean match(String s, String p, int i, int j) {
        // s为空，所以默认任何[0][*]都为false，但[0][2]可能是true
        if (i == 0)
            return false;
        else if (p.charAt(j - 1) == '.')
            return true;
        else
            return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
