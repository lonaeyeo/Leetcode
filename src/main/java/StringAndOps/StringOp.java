package StringAndOps;

import Tree.ListNode;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StringOp {

    /**
     * 剑指05.替换空格
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            } else {
                sb.append("%20");
            }
        }
        return sb.toString();
    }

    /**
     * 剑指06.从尾到头打印链表
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[0];
        List<Integer> list = new ArrayList<>();
        rPrint(head, list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public void rPrint(ListNode head, List<Integer> list) {
        if (head != null) {
            return;
        }
        rPrint(head.next, list);
        list.add(head.val);
    }
}
