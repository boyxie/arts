package algorithms;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        //        ListNode l2 = new ListNode(2);
        //        l2.next = new ListNode(3);
        //        l2.next.next = new ListNode(4);
        //        ListNode node = mergeTwoLists(l1, l2);
        ListNode node = removeNthFromEnd(l1, 2);
        String s = node.val + "";
        while (node.next != null) {
            System.out.println(s += "->" + node.next.val);
            node.next = node.next.next;
        }

        long sum = 1;
        for (int i = 0; i <= 48; i++) {
            sum = sum * 2;
        }
        System.out.println(sum/8/1024/1024/1024/1024);
    }


    public static void testStatic(){
         int a = 5;

    }


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode preListNode = null;
        if (l1.val < l2.val) {
            preListNode = l1;
            preListNode.next = mergeTwoLists(l1.next, l2);
        } else {
            preListNode = l2;
            preListNode.next = mergeTwoLists(l1, l2.next);
        }
        return preListNode;

    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return null;
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        //if remove the first node
        if (fast == null) {
            head = head.next;
            return head;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
