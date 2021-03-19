package BinaryTreeAndNode;

import Tree.ListNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class ListOperation {

    /**
     * 逆反链表特定的一段
     */
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

    /**
     * 链表两数相加
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode list = new ListNode(0);
        ListNode res = list;
        int forward = 0;

        // 两链表相加
        while (l1 != null && l2 != null) {
            int oneDigit = (l1.val + l2.val + forward) % 10;
            list.next = new ListNode(oneDigit);
            forward = (l1.val + l2.val + forward) / 10;
            list = list.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode lLeft;
        if (l1 == null && l2 == null) lLeft = null;
        else if (l1 == null) lLeft = l2;
        else lLeft = l1;

        // 将剩余链表相加
        while (lLeft != null) {
            int oneDigit = (forward + lLeft.val) % 10;
            list.next = new ListNode(oneDigit);
            forward = (forward + lLeft.val) / 10;
            list = list.next;
            lLeft = lLeft.next;
        }
        if (forward > 0) {
            list.next = new ListNode(forward);
        }
        return res.next;
    }

    @Test
    public void test1() {
        ListOperation l = new ListOperation();
        ListNode head = new ListNode(1);
        ListNode l1 = head.buildList(new int[]{3, 5});
        ListNode l2 = head.buildList(new int[]{9, 2, 3, 4});
        head = l.addTwoNumbers(l1, l2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

}
