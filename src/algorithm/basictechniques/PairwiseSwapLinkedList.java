//https://www.geeksforgeeks.org/pairwise-swap-elements-of-a-given-linked-list/
package algorithm.basictechniques;

public class PairwiseSwapLinkedList {
    static Node head;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    Node pairwiseSwapLinkedList(Node n) {
        if (n != null && n.next != null) {
            Node result = pairwiseSwapLinkedList(n.next.next);
            Node nextNode = n.next;
            nextNode.next = n;
            n.next = result;
            return nextNode;
        }
        return n;
    }

    public static void main(String[] args) {
        PairwiseSwapLinkedList list = new PairwiseSwapLinkedList();
        list.head = new Node(1);
        list.head.next = new Node(2);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(4);
        list.head.next.next.next.next = new Node(5);
        list.head.next.next.next.next.next = new Node(6);

        System.out.println("Original Linked list ");
        list.printList(head);
        head = list.pairwiseSwapLinkedList(head);
        System.out.println("");
        System.out.println("");
        System.out.println("Pairwise Swapped linked list ");
        list.printList(list.head);
    }
}
