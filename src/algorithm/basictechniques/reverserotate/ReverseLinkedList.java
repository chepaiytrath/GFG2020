//https://www.geeksforgeeks.org/reverse-a-linked-list/
package algorithm.basictechniques.reverserotate;

public class ReverseLinkedList {
    static Node head;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    void reverseLinkedList(Node curr, Node prev) {
        if (curr.next == null) {
            head = curr;
            curr.next = prev;
            return;
        }
        Node next1 = curr.next;
        curr.next = prev;
        reverseLinkedList(next1, curr);
    }

    public static void main(String[] args) {
        ReverseLinkedList list = new ReverseLinkedList();
        list.head = new Node(1);
        list.head.next = new Node(2);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(4);
        list.head.next.next.next.next = new Node(5);
        list.head.next.next.next.next.next = new Node(6);
        list.head.next.next.next.next.next.next = new Node(7);
        list.head.next.next.next.next.next.next.next = new Node(8);

        System.out.println("Original Linked list ");
        list.printList(head);
        list.reverseLinkedList(head, null);
        System.out.println("");
        System.out.println("");
        System.out.println("Reversed linked list ");
        list.printList(list.head);
    }
}
