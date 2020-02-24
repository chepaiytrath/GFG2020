//https://www.geeksforgeeks.org/remove-duplicates-from-an-unsorted-linked-list/
package company.intuit;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesFromUnsortedLinkedList {
    static Node head;

    static class Node {
        int data;
        Node next;

        Node(int val) {
            this.data = val;
        }
    }
    void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    void removeDuplicates(Node head) {
        Node node = head;
        Set<Integer> set = new HashSet<>();
        set.add(node.data);
        Node curr = node.next;
        Node prev = node;

        while (curr != null) {
            if (set.contains(curr.data)) {
                Node nextNode = curr.next;
                prev.next = nextNode;
                curr = nextNode;
            } else {
                set.add(curr.data);
                prev = curr;
                curr = curr.next;
            }
        }
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromUnsortedLinkedList list = new RemoveDuplicatesFromUnsortedLinkedList();
        list.head = new Node(5);
        list.head.next = new Node(3);
        list.head.next.next = new Node(4);
        list.head.next.next.next = new Node(2);
        list.head.next.next.next.next = new Node(5);
        list.head.next.next.next.next.next = new Node(4);
        list.head.next.next.next.next.next.next = new Node(1);
        list.head.next.next.next.next.next.next.next = new Node(3);

        System.out.println("Original Linked list ");
        list.printList(head);
        list.removeDuplicates(head);
        System.out.println("");
        System.out.println("");
        System.out.println("After removing duplicates linked list ");
        list.printList(list.head);
    }
}
