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
        // 注意：""和"a*b*c*"也是匹配的
        fp[0][0] = true;

        // [*][0]都是false
        // i从0开始，是因为可能有""和".*"这种情况
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    // 如果s的第i个和p的第j-1个相同
                    if (match(s, p, i, j - 1))
                        // 此处保留fp[i][j - 2]是因为存在"ba","baa*"这种情况，即"a*"不进行不匹配
                        // fp[i-1][j]其实就是[i-1][j-2]，保留j是为了可以多匹配
                        fp[i][j] = fp[i - 1][j] || fp[i][j - 2];
                    else
                        // 如果s的[i]和'*'前面的[j-1]不相同，那么".*"是不进行匹配的，即完全取决于fp[i][j-2]
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
        // s为空，所以默认任何[0][n]都为false，注意此处[n]不包括'*'
        if (i == 0)
            return false;
        else if (p.charAt(j - 1) == '.')
            return true;
        else
            return s.charAt(i - 1) == p.charAt(j - 1);
    }


    /**
     * 通配符匹配
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     */
    public boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();
        // 0表示字符串长度为零，所以s的对应index=i-1
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 这一部分也是预处理部分
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*')
                //
                dp[0][j] = dp[0][j - 1];
            else
                // 如果""和"****a***"中出现一次非'*'，那么意味着后面都是错的，直接break就好了。
                break;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果第j个为'*'，那么可匹配0个，也可匹配n个
                // 如果第j个为'?'，那么可匹配任意1个
                // 如果第j个为字母，那么看是否可匹配
                if (p.charAt(j - 1) == '*')
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                else if (p.charAt(j - 1) == '?')
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = s.charAt(i - 1) == p.charAt(j - 1) && dp[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }
}
