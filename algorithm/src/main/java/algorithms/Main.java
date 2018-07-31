package algorithms;

public class Main {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode node = mergeTwoLists(l1, l2);
        String s = node.val+"";
        while (node.next != null) {
            System.out.println(s += "->"+node.next.val);
            node.next = node.next.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if (l1==null) return l2;
        if (l2==null) return l1;
        while (l1.next != null) {
            while (l2.val <= l1.next.val) {
                ListNode tmp;
                tmp = l1.next.next;
                l1.next.next = l2.next;
                l2.next=
            }
            l1.next = l1.next.next;
        }
        return l1;
    }
}
