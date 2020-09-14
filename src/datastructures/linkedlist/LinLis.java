package datastructures.linkedlist;

public class LinLis {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    static class DLLNode{
        int data;
        DLLNode next;
        DLLNode prev;
        DLLNode(int data){
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(12);
        head.next = new Node(15);
        head.next.next = new Node(10);
        head.next.next.next = new Node(11);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next.next = new Node(3);

/*
        DLLNode node1 = new DLLNode(1);
        DLLNode node2 = new DLLNode(2);
        DLLNode node3 = new DLLNode(3);
        DLLNode node4 = new DLLNode(4);
        DLLNode node5 = new DLLNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node2.prev = node1;
        node3.prev = node2;
        node4.prev = node3;
        node5.prev = node4;


        DLLNode res = swapkthnode(node1, 3);
        printDLLList(res);*/



        compute(head);
        printList(head);
    }

    static Node compute(Node head)
    {
        // your code here

        head = reverse(head);

        Node curr = head;
        Node max = head;
        while(curr != null && curr.next != null){
            if(curr.next.data < max.data){
                curr.next = curr.next.next;
            }else{
                curr = curr.next;
                max = curr;
            }
        }

        head = reverse(head);
        return head;
    }

    private static Node reverse(Node head){
        Node prev = null;
        Node curr = head;
        while(curr != null){
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }


    private static void printList(Node res) {
        while (res != null) {
            System.out.print(res.data + " ");
            res = res.next;
        }
    }

    private static void printDLLList(DLLNode res) {
        while (res != null) {
            System.out.print(res.data + " ");
            res = res.next;
        }
    }
}
