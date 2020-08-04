package algorithm.manipulateds;

import java.util.HashMap;
import java.util.Map;

public class CloneLinkedListWithNextAndRandomPointers {
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

    public static void main(String[] args) {
        LinkedListDemo.Node n1 = new LinkedListDemo.Node(1);
        LinkedListDemo.Node n2 = new LinkedListDemo.Node(2);
        LinkedListDemo.Node n3 = new LinkedListDemo.Node(3);
        LinkedListDemo.Node n4 = new LinkedListDemo.Node(4);
        LinkedListDemo.Node n5 = new LinkedListDemo.Node(5);

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

    private static LinkedListDemo cloneList(LinkedListDemo l1) {
        LinkedListDemo.Node curr = l1.head;
        LinkedListDemo.Node newHead = null;
        Map<LinkedListDemo.Node, LinkedListDemo.Node> cloneLink = new HashMap<>();
        while (curr != null) {
            LinkedListDemo.Node newNode = new LinkedListDemo.Node(curr.data);
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
}
