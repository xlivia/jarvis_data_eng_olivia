class RemoveFromEnd {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        // Move the fast pointer n steps ahead
        for (int i = 0; i <= n; i++) {
            if (fast == null) {
                return head;
            }
            fast = fast.next;
        }

        // Move the slow and fast pointers simultaneously
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the nth node from the end
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int n = 2;

        // Remove the nth node from the end
        ListNode result = removeNthFromEnd(head, n);

        // Print the modified linked list
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}