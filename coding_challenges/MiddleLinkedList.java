class MiddleLinkedList {

    public class ListNode {
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

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main (String[] args) {
        MiddleLinkedList solution = new MiddleLinkedList();
        // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode node5 = solution.new ListNode(5);
        ListNode node4 = solution.new ListNode(4, node5);
        ListNode node3 = solution.new ListNode(3, node4);
        ListNode node2 = solution.new ListNode(2, node3);
        ListNode node1 = solution.new ListNode(1, node2);
        ListNode result = solution.middleNode(node1);
        // Print the middle node and the subsequent nodes
        ListNode current = result;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

}