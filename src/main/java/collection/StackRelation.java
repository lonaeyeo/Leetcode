package collection;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class StackRelation {

    /**
     * 剑指 Offer 31. 栈的压入、弹出序列
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) return false;
        if (pushed.length == 0) return true;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pushed.length; i++) {
            map.put(pushed[i], i);
        }

        Deque<Integer> stack = new LinkedList<>();
        // 用于标记pushed的index，起始位置应为-1
        int index = -1;
        for (int i = 0; i < popped.length; i++) {
            int curr = map.get(popped[i]);

            // 如果curr>index，说明之前的都是按顺序进栈
            if (curr > index) {
                index++;
                while (index < curr) {
                    stack.push(index);
                    index++;
                }
            } else {
                if (stack.pop() == curr)
                    continue;
                else
                    return false;
            }
        }

        return true;
    }

    // 就按栈思路走
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<>();

        int i = 0;
        // 一个一个入栈，并判断出栈的可能性
        for (int curr : pushed) {
            stack.push(curr);
            // 循环是因为存在一连串满足条件的出栈,例如poped=[3,2,1]
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }

        // 如果栈为空，说明popped是一种出栈方式
        return stack.isEmpty();
    }
}
