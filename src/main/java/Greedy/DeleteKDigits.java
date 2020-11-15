package Greedy;

import org.junit.Test;

import javax.print.attribute.standard.NumberUp;
import java.util.*;

public class DeleteKDigits {

    @Test
    public void test() {
//        char a = '0';
//        System.out.println((int) a);
//        int b = 48;
//        System.out.println((char) b);
//        String s1 = "";
//        System.out.println(s1.length());
        String str = "1432219";
        DeleteKDigits deleteKDigits = new DeleteKDigits();
        System.out.println(deleteKDigits.removeKdigits(str, 3));
    }

    public String removeKdigits(String num, int k) {
        int[] Nums = new int[num.length()];
        Deque<Integer> stack = new LinkedList<>();
        StringBuffer ans = new StringBuffer();
        int remain = k;

        for (int i = 0; i < Nums.length; ++i) {
            Nums[i] = (int) num.charAt(i) - 48;
        }

        // 贪心，栈
        for (int i = 0; i < Nums.length; ) {
            if (i == 0 || stack.isEmpty()) {
                stack.push(Nums[i]);
                ++i;
                continue;
            }
            if (stack.peek() > Nums[i] && remain > 0) {
                stack.pop();
                remain = remain - 1;
            } else {
                stack.push(Nums[i]);
                ++i;
            }
        }
        for (; remain > 0; --remain) {
            stack.pop();
        }

        int flag = 0;
        while (!stack.isEmpty()) {
            if (stack.peekLast() == 0 && flag == 0) {
                stack.pollLast();
                continue;
            }
            int temp = stack.pollLast() + 48;
            flag = 1;
            ans.append((char) temp);
        }

        if (ans.toString().length() == 0)
            return "0";
        return ans.toString();
    }
}
