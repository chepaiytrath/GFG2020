package algorithm.manipulateds;

import algorithm.manipulateds.CloneLinkedListWithNextAndRandomPointers.LinkedListDemo.Node;

import java.util.HashMap;
import java.util.Map;

public class CloneLinkedListWithNextAndRandomPointers {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        LinkedListDemo l1 = new LinkedListDemo();
        l1.pushToEnd(n1);
        l1.pushToEnd(n2);
        l1.pushToEnd(n3);
        l1.pushToEnd(n4);
        l1.pushToEnd(n5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        n1.random = n3;
        n3.random = n5;
        n2.random = n1;
        n4.random = n3;
        n5.random = n2;

        cloneList(l1).printList();
        System.out.println("CLONED SUCCESSFULLY");
    }

    // HASHING BASED TECHNIQUE
    private static LinkedListDemo cloneList(LinkedListDemo l1) {
        Node curr = l1.head;
        Map<Node, Node> cloneLink = new HashMap<>();
        while (curr != null) {
            Node newNode = new Node(curr.data);
            cloneLink.put(curr, newNode);
            curr = curr.next;
        }

        curr = l1.head;
        while (curr != null) {
            cloneLink.get(curr).next = cloneLink.get(curr.next);
            cloneLink.get(curr).random = cloneLink.get(curr.random);
            curr = curr.next;
        }

        LinkedListDemo l2 = new LinkedListDemo();
        l2.head = cloneLink.get(l1.head);
        return l2;
    }






    // DON'T FRET, THIS IS JUST A SAMPLE LINKED LIST CLASS
    static class LinkedListDemo {
        Node head;

        LinkedListDemo() {
            this.head = null;
        }

        LinkedListDemo(int data) {
            this.head = new Node(data);
        }

        static class Node {
            int data;
            Node next;
            Node random;

            Node(int data) {
                this.data = data;
                next = null;
                random = null;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "data=" + data +
                        '}';
            }
        }

        public String printList() {
            Node n = head;
            StringBuffer sb = new StringBuffer();
            while (n != null) {
                sb.append(n.data + " "+n.random+"    ");
                n = n.next;
            }
            System.out.println(sb.toString());
            return sb.toString();
        }

        public void pushToEnd(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                return;
            }
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }

        public void pushToEnd(Node node) {
            if (head == null) {
                head = node;
                return;
            }
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = node;
        }
    }
}
