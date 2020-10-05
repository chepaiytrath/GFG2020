package datastructures.linkedlist;

public class DoublyLinkedList {
    static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
        }
    }

    /*public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node2.prev = node1;
        node3.prev = node2;
        node4.prev = node3;
        node5.prev = node4;


        Node res = deleteNodeAtIndex(node1, 3);
        printCLL(res);
    }*/

    public static Node deleteNodeAtIndex(Node head, int x) {
        /* SAMPLE INPUT
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node2.prev = node1;
        node3.prev = node2;
        node4.prev = node3;
        node5.prev = node4;*/

        if (x == 1) {
            return head.next;
        }

        Node curr = head;
        int count = 1;
        while (count < x && curr != null) {
            curr = curr.next;
            count++;
        }

        curr.prev.next = curr.next;
        if (curr.next != null) {
            curr.next.prev = curr.prev;
        }
        curr.prev = null;
        curr.next = null;
        return head;
    }

    private static void printCLL(Node res) {
        while (res != null) {
            System.out.print(res.data + " ");
            res = res.next;
        }
    }

    public static void main(String[] args) {
        reverseDoublyLinkedList();
    }

    public static void reverseDoublyLinkedList() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node2.prev = node1;
        node3.prev = node2;
        node4.prev = node3;
        node5.prev = node4;
        Node revHead = reverseDoublyLinkedListUtil(node1);
        printCLL(revHead);
    }

    private static Node reverseDoublyLinkedListUtil(Node node) {
        if (node == null) {
            return null;
        }

        // Using separate prev variable : Similar to reversing a Singly Linked List
        /*Node curr = node;
        Node prev = null;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            if (prev != null) {
                prev.prev = curr;
            }
            prev = curr;
            curr = next;
        }
        prev.prev = null;
        return prev;*/


        // Without Using separate prev variable : Actually using power of DLL
        // Simply swap prev and next fields of object

        Node curr = node;
        Node temp = null;
        while (curr != null) {              // Is null safe for first node in the list
            temp = curr.prev;
            curr.prev = curr.next;          // What was next is now previous
            curr.next = temp;               // What was previous is now next
            curr = curr.prev; // Or maintain a reference of next node earlier and then move to that next node
        }

        if (temp != null) {                // temp holds the second last node whose previous is the last node in reverse DLL
            return temp.prev;
        }
        return null;
    }


}
