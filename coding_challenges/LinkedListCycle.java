public class LinkedListCycle {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();
        // Create a sample linked list with a cycle: 3 -> 2 -> 0 -> -4 -> 2
        ListNode node4 = solution.new ListNode(-4);
        ListNode node3 = solution.new ListNode(0);
        ListNode node2 = solution.new ListNode(2);
        ListNode node1 = solution.new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        boolean result = solution.hasCycle(node1);
        System.out.println("Has cycle: " + result);
    }

}