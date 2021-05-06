package DP;

public class CountDigitOne {

    public static void main(String[] args) {
        CountDigitOne c = new CountDigitOne();
        System.out.println(c.countDigitOne(12));
    }

    /**
     * 剑指 Offer 43. 1～n 整数中 1 出现的次数
     * 分类讨论：当前位数字为0、1或者其他
     * 0: high * digit
     * 1: high * digit + low + 1
     * >=2: (high + 1) * digit
     * 计算每一位的1的存在数
     */
    public int countDigitOne(int n) {
        String nstr = String.valueOf(n);
        char curr;
        int res = 0;
        int digit = 0;

        for (int i = nstr.length() - 1; i >= 0; i--) {
            curr = nstr.charAt(i);
            int high = i > 0 ? Integer.parseInt(nstr.substring(0, i)) : 0;
            int low = i < nstr.length() - 1 ? Integer.parseInt(nstr.substring(i + 1)) : 0;
            if (curr == '0') {
                res += high * Math.pow(10, digit);
            } else if (curr == '1') {
                res += high * Math.pow(10, digit) + low + 1;
            } else {
                res += (high + 1) * Math.pow(10, digit);
            }
            digit++;
        }

        return res;
    }
}
