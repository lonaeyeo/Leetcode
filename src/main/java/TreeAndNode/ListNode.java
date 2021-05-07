package TreeAndNode;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode buildList(int[] vals) {
        ListNode pre = new ListNode(vals[0]);
        ListNode head = pre;
        for (int i = 1; i < vals.length; ++i) {
            ListNode listNode = new ListNode(vals[i]);
            pre.next = listNode;
            pre = listNode;
        }
        return head;
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null)
            return head;
        ListNode atomicHead = new ListNode(0);
        atomicHead.next = head;
        ListNode curr = head.next;
        ListNode lastSorted = head;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = curr;
                curr = curr.next;
            } else {
                ListNode prev = atomicHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
                curr = lastSorted.next;
            }
        }
        return atomicHead.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode h = head;
        ListNode pre = null;
        ListNode curr = null;

        while (h != null) {
            curr = h;
            h = h.next;
            curr.next = pre;
            pre = curr;
        }

        return curr;
    }
}
