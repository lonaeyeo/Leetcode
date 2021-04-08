package Stack;

import java.util.*;

public class Calculator {
    /**
     * 算术计算
     * 非常好的栗子
     * 从输入处理开始就很棒
     * 这道题的思路：
     * 扫描字符串：如果是数字，查看后续是不是数字，是就组成一个数字，要不然暂停；
     * else 如果ops里有操作符，并且str当前的字符优先级小，那么取出ops的置顶并计算；（此步会循环，没有任何ops.push()的操作）；
     * else 如果ops里无操作符，那就push进str当前位置的op，并判断是否为'.'，若是则计算结束。
     */
    public static void main(String[] args) {
        // 10000+99/9-100*100
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        str = str + ".";
        Deque<Integer> nums = new LinkedList<>();
        Deque<Character> ops = new LinkedList<>();

        int index = 0;
        while (str.length() > index) {
            if (Character.isDigit(str.charAt(index))) {
                // 判断是否为数字
                int temp = 0;
                while (str.length() > index && Character.isDigit(str.charAt(index))) {
                    temp = temp * 10 + Integer.parseInt(str.substring(index, index + 1));
                    index++;
                }
                nums.push(temp);
            } else if (!ops.isEmpty() && isPrior(ops.peek(), str.charAt(index))) {
                // 判断str当前操作符优先级是否低于ops.peek()
                // 重点：此处没有任何push操作
                char op = ops.pop();
                int a1 = nums.pop();
                int a2 = nums.pop();
                // 计算值
                compute(op, a1, a2, nums);
            } else {
                // 如果ops是空||str优先级高的话，直接push操作符
                if (str.charAt(index) == '.') {
                    break;
                } else {
                    ops.push(str.charAt(index));
                    index++;
                }
            }
        }
        System.out.println(nums.peek());
    }

    // 判断优先级，优先级'*/' > '-' > '+' > '.'
    public static boolean isPrior(char a, char b) {
        if (a == '/' || a == '*') {
            return true;
        } else if (b == '/' || b == '*') {
            return false;
        } else if (a == '-') {
            return true;
        } else if (b == '.') {
            return true;
        } else {
            return false;
        }

    }

    // 基本计算
    public static void compute(char op, int a1, int a2, Deque<Integer> nums) {
        if (op == '*') {
            nums.push(a2 * a1);
        } else if (op == '/') {
            nums.push(a2 / a1);
        } else if (op == '-') {
            nums.push(a2 - a1);
        } else {
            nums.push(a2 + a1);
        }
    }

    public static void print(Deque<Integer> nums, Deque<Character> ops) {
        for (char op : ops) {
            System.out.print(op + " ");
        }
        System.out.println();
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
