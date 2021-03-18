package BinaryTree;

import Tree.ListNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class ListOperation {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right)
            return head;
        Deque<ListNode> stack = new LinkedList<>();
        ListNode curr = head;
        ListNode prev = new ListNode(0);
        prev.next = head;
        // 定位到left
        int index = 1;
        while (index < left) {
            prev = prev.next;
            curr = curr.next;
            index++;
        }
        // 将[left, right]之间的入栈
        index = left;
        while (index <= right) {
            stack.push(curr);
            curr = curr.next;
            index++;
        }
        ListNode next = curr;
        // 出栈就好了，origin记录head的前一个位置
        ListNode origin = new ListNode(0);
        // 如果第一个点就需要调换位置，那么就得记住新第一个点的位置，栈提供了这种思路
        origin.next = left == 1 ? stack.peek() : head;
        while (!stack.isEmpty()) {
            prev.next = stack.pop();
            prev = prev.next;
        }
        prev.next = next;
        return origin.next;
    }

    @Test
    public void test() {
        ListOperation l = new ListOperation();
        ListNode head = new ListNode(1);
        head = head.buildList(new int[]{3, 5});
        head = l.reverseBetween(head, 1, 2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
