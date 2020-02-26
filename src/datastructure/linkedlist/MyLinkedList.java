package datastructure.linkedlist;

import java.util.Stack;

public class MyLinkedList {
    Node head;
    Node left; //Used in checkIfPalindromeUsingRecursion

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public void printList() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
    }

    public void pushToStart(int data) {
        Node newNode = new Node(data);
        /*REDUNDANT
        if (head == null) {
            head = newNode;
            return;
        }*/
        newNode.next = head;
        head = newNode;
    }

    public void pushAfterNode(Node node, int data) {
        if (node == null) {
            System.out.println("Given Node cannot be null");
            return;
        }
        Node newNode = new Node(data);
        newNode.next = node.next;
        node.next = newNode;
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

    public void deleteNodeByValue(int data) {
        if (head == null) {
            System.out.println("LinkedList is empty. Cannot Delete.");
            return;
        }
        if (head.data == data) {
            head = head.next;
            return;
        }

        Node curr = head;
        Node prev = null;

        while (curr != null && curr.data != data) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != null) {
            prev.next = curr.next;
        }
    }

    public void deleteNodeByPosition(int position) {
        if (position < 0) {
            System.out.println("\nInvalid position");
            return;
        }
        if (head == null) {
            return;
        }
        if (position == 0) {
            head = head.next;
            return;
        }

        Node prev = null;
        Node curr = head;
        for (int i = 0; i < position && curr != null; i++) {
            prev = curr;
            curr = curr.next;
        }
        if (curr == null) {
            System.out.println("\nNot enough elements in linked list");
            return;
        }
        if (curr != null) {
            prev.next = curr.next;
        }
    }

    public boolean searchIteratively(int data) {
        if (head == null) {
            return false;
        }
        Node curr = head;
        while (curr != null) {
            if (curr.data == data) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public boolean searchRecursively(Node node, int data) {
        if (node == null) {
            return false;
        }
        if (node.data == data) {
            return true;
        }
        return searchRecursively(node.next, data);
    }

    public int getNthNodeIteratively(int count) {
        if (count < 0) {
            System.out.println("Invalid count");
            return -1;
        }
        Node curr = head;
        for (int i = 0; i < count && curr != null; i++) {
            curr = curr.next;
        }
        if (curr != null) {
            return curr.data;
        }
        return -1;
    }

    public int getNthNodeRecursively(Node node, int count) {
        if (count == 0 && node != null) {
            return node.data;
        }
        if (node == null) {
            return -1;
        }
        return getNthNodeRecursively(node.next, count - 1);
    }

    public int getNthNodeFromEnd(int count) {
        //Using two pointers
        Node p1 = head;
        Node p2 = head;

        for (int i = 0; i < count - 1; i++) {
            p1 = p1.next;
            if (p1 == null) {
                return -1;
            }
        }
        while (p1 != null && p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2.data;
    }

    public int findMiddleElement() {
        //Using two pointers
        if (head == null) {
            return -1;
        }

        Node p1 = head, p2 = head;
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2.data;
    }

    public int countFrequencyOfGivenElement(Node node, int data) {
        if (node == null) {
            return 0;
        }
        if (node.data == data) {
            return 1 + countFrequencyOfGivenElement(node.next, data);
        } else {
            return 0 + countFrequencyOfGivenElement(node.next, data);
        }
    }

    public boolean detectLoop() {
        Node p1 = head, p2 = head;
        while (p2 != null && p2.next != null) {
            p2 = p2.next.next;
            p1 = p1.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfPalindrome() {
        Node curr = head;
        Stack<Integer> s = new Stack<>();
        while (curr != null) {
            s.add(curr.data);
            curr = curr.next;
        }
        curr = head;
        for (int i = 0; i < s.size(); i++) {
            if (s.pop() != curr.data) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }

    public boolean checkIfPalindromeUsingRecursion() {
        Node curr = head;
        return isPalindromeUtil(curr);
    }

    private boolean isPalindromeUtil(Node right) {
        left = head;
        if (right == null) {
            return true;
        }
        boolean isP = isPalindromeUtil(right.next);
        if (!isP) {
            return false;
        }
        boolean isP2 = left.data == right.data;
        left = left.next;
        return isP2;
    }

    public void removeDuplicatesFromSortedLinkedListIteratively() {
        if (head == null) {
            return;
        }
        Node curr = head;
        while (curr != null) {
            Node temp = curr;
            while (temp != null && curr != null && temp.data == curr.data) {
                temp = temp.next;
            }
            curr.next = temp;
            curr = curr.next;
        }
    }

    public void removeDuplicatesFromSortedLinkedListRecursively() {
        Node curr = head;
        removeDuplicatesUtil(curr);
    }

    private void removeDuplicatesUtil(Node curr) {
        if (curr == null) {
            return;
        }
        if (curr.next != null) {
            if (curr.data == curr.next.data) {
                Node to_free = curr.next;
                curr.next = to_free.next;
                to_free.next = null;
                removeDuplicatesUtil(curr);
            } else {
                removeDuplicatesUtil(curr.next);
            }
        }
    }

    public void swapNodesWithoutSwappingData(int xData, int yData) {
        if (xData == yData) return;
        Node xPrev = null, yPrev = null, x = head, y = head;
        while (x != null && x.data != xData) {
            xPrev = x;
            x = x.next;
        }

        while (y != null && y.data != yData) {
            yPrev = y;
            y = y.next;
        }

        if (x == null || y == null)
            return;


        if (xPrev != null) xPrev.next = y;
        else head = y;

        if (yPrev != null) yPrev.next = x;
        else head = x;

        Node xNext = x.next;
        Node yNext = y.next;
        y.next = xNext;
        x.next = yNext;
    }

    public void pairwiseSwapElements(Node node) {
        if(node == null || node.next == null){
            return;
        }
        swapData(node, node.next);
        pairwiseSwapElements(node.next.next);
    }

    private void swapData(Node node, Node next) {
        int temp = node.data;
        node.data = next.data;
        next.data = temp;
    }
}