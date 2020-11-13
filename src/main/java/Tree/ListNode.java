package Tree;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }
    public ListNode buildList(int[] vals) {
        ListNode pre = new ListNode(vals[0]);
        ListNode head = pre;
        for (int i = 1; i < vals.length; ++i){
            ListNode listNode = new ListNode(vals[i]);
            pre.next = listNode;
            pre = listNode;
        }
        return head;
    }
}
