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
        String s = sc.nextLine();
        Calculator c = new Calculator();
        System.out.println(c.calculate(s));
    }

    public int calculate2(String s) {
        s = s + ".";
        Deque<Integer> nums = new LinkedList<>();
        Deque<Character> ops = new LinkedList<>();

        int index = 0;
        while (s.length() > index) {
            if (Character.isDigit(s.charAt(index))) {
                // 判断是否为数字
                int temp = 0;
                while (s.length() > index && Character.isDigit(s.charAt(index))) {
                    temp = temp * 10 + s.charAt(index) - '0';
                    index++;
                }
                nums.push(temp);
            } else if (!ops.isEmpty() && isPrior(ops.peek(), s.charAt(index))) {
                // 判断str当前操作符优先级是否低于ops.peek()
                // 重点：此处没有任何push操作
                char op = ops.pop();
                int a1 = nums.pop();
                int a2 = nums.pop();
                // 计算值
                compute(op, a1, a2, nums);
            } else {
                // 如果ops是空||str优先级高的话，直接push操作符
                if (s.charAt(index) == '.') {
                    break;
                } else {
                    ops.push(s.charAt(index));
                    index++;
                }
            }
        }
        //System.out.println(nums.peek());
        return nums.peek();
    }

    // 判断优先级，优先级'*/' > '-' > '+' > '.'
    public boolean isPrior(char a, char b) {
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
    public void compute(char op, int a1, int a2, Deque<Integer> nums) {
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


    /**
     * 方法二：单栈
     * 先计算乘除，减法则负值，加法不处理
     * preSign 标记当前数前一个运算符
     * 剩余栈直接加法就🆗
     */
    public int calculate(String s) {
        // 初试化preSign
        char preSign = '+';
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            // 处理空格
            if (s.charAt(i) == ' ') {
                continue;
            } else if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                // 抵消i+1效果
                i--;
                // 根据preSign处理当前num
                if (preSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (preSign == '/') {
                    stack.push(stack.pop() / num);
                } else if (preSign == '-') {
                    stack.push(-num);
                } else {
                    stack.push(num);
                }

            } else {
                preSign = s.charAt(i);
            }
        }

        // 加法处理剩余所有nums
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

}
