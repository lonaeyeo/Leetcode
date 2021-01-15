package BinarySearch;

import org.junit.Test;

public class BinarySearchOperation {

    @Test
    public void test() {
        BinarySearchOperation binarySearchOperation = new BinarySearchOperation();
        System.out.println(Integer.MIN_VALUE/1);
        System.out.println(Integer.MIN_VALUE/-1);
        System.out.println(Integer.MIN_VALUE);
//        System.out.println(binarySearchOperation.divide(Integer.MIN_VALUE, -1));
    }

    /**
     * 两数相除，不能用乘除法
     * divisor二倍变化来逼近dividend
     */
    public int divide(int dividend, int divisor) {
        if (divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;

        int sign = (dividend ^ divisor) < 0 ? -1 : 1;
        // 将数字转换成负数来处理，更好应对溢出情况 [2^32, 2^32-1]
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;

        int ans = divideHelper(dividend, divisor);
        return sign < 0 ? -ans : ans;
    }

    private int divideHelper(int dividend, int divisor) {
        if (dividend > divisor) return 0;

        int count = 1;
        int tmp = divisor;

        // tmp+tmp<0 处理负数因溢出而变成正数的问题
        while (tmp + tmp < 0 && tmp + tmp >= dividend) {
            count += count;
            tmp += tmp;
        }
        return count + divideHelper(dividend - tmp, divisor);
    }

}
