import java.util.HashSet;

public class DuplicateLLNode {

    public static class LinkedListNode {
        int val;
        LinkedListNode next;
        LinkedListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void removeDuplicates(LinkedListNode head) {
        if (head == null) {
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        LinkedListNode current = head;
        LinkedListNode previous = null;
        while (current != null) {
            if (set.contains(current.val)) {
                // Duplicate node found, skip it
                previous.next = current.next;
            }
            else {
                // Add value to set and move pointers
                set.add(current.val);
                previous = current;
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        LinkedListNode node1 = new LinkedListNode(1);
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node3 = new LinkedListNode(2);
        LinkedListNode node4 = new LinkedListNode(3);
        LinkedListNode node5 = new LinkedListNode(4);
        LinkedListNode node6 = new LinkedListNode(4);
        LinkedListNode node7 = new LinkedListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        System.out.println("Before removing duplicates:");
        printLinkedList(node1);
        removeDuplicates(node1);
        System.out.println("After removing duplicates:");
        printLinkedList(node1);
    }

    private static void printLinkedList(LinkedListNode head) {
        LinkedListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

}