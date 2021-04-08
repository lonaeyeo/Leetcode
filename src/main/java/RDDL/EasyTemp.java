package RDDL;

import Tree.ListNode;
import org.junit.Test;

public class EasyTemp {

    @Test
    public void test() {
        int[] a = new int[]{1, 2, 3, 4, 5};
        ListNode l = new ListNode(1);
        EasyTemp easyTemp = new EasyTemp();
        ListNode head = l.buildList(a);
        ListNode ans = easyTemp.oddEvenList(new ListNode(1));
        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }

    }

    public ListNode oddEvenList(ListNode head) {
        int index = 3;
        if (head == null || head.next == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode even = head.next;
        ListNode oddHead = head;
        ListNode odd = head;

        head = even.next;
        while (head != null) {
            if (index % 2 != 0) {
                odd.next = head;
                odd = odd.next;
            } else {
                even.next = head;
                even = even.next;
            }
            ++index;
            head = head.next;
        }
        even.next = null;
        odd.next = evenHead;
        return oddHead;
    }
}
