class RemoveFromEnd {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // Create a dummy node
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        // Move the fast pointer n steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        // Move fast and slow pointers simultaneously
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // Remove the nth node from the end
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        RemoveFromEnd solution = new RemoveFromEnd();
        int n = 2;
        ListNode result = solution.removeNthFromEnd(node1, n);
        // Print the modified linked list
        ListNode current = result;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

}