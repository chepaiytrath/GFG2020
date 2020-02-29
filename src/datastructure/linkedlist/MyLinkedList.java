package datastructure.linkedlist;

import java.util.Stack;

public class MyLinkedList {
    Node head;
    Node tail;
    Node left; //Used in checkIfPalindromeUsingRecursion

    MyLinkedList(Node node) {
        this.head = node;
    }

    MyLinkedList(int data) {
        this.head = new Node(data);
    }

    MyLinkedList() {
    }

    /*@Override
    public String toString() {
        return printList();
    }*/

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

    public String printList() {
        Node n = head;
        StringBuffer sb = new StringBuffer();
        while (n != null) {
            sb.append(n.data + " ");
            n = n.next;
        }
        System.out.println(sb.toString());
        return sb.toString();
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

    public boolean checkIfPalindromeRecursively() {
        Node curr = head;
        return checkIfPalindromeRecursivelyUtil(curr);
    }

    private boolean checkIfPalindromeRecursivelyUtil(Node right) {
        left = head;
        if (right == null) {
            return true;
        }
        boolean isP = checkIfPalindromeRecursivelyUtil(right.next);
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

    public void removeDuplicatesFromSortedLLRecursively() {
        Node curr = head;
        removeDuplicatesFromSortedLLRecursivelyUtil(curr);
    }

    private void removeDuplicatesFromSortedLLRecursivelyUtil(Node curr) {
        if (curr == null) {
            return;
        }
        if (curr.next != null) {
            if (curr.data == curr.next.data) {
                Node nextNode = curr.next;
                curr.next = nextNode.next;
                nextNode.next = null;
                removeDuplicatesFromSortedLLRecursivelyUtil(curr);
            } else {
                removeDuplicatesFromSortedLLRecursivelyUtil(curr.next);
            }
        }
    }

    public void swapNodesWithoutSwappingData(int xData, int yData) {
        if (xData == yData) {
            return;
        }
        Node curr = head, xPrev = null, yPrev = null;
        while (curr.next != null) {
            if (curr.next.data == xData) {
                xPrev = curr;
            } else if (curr.next.data == yData) {
                yPrev = curr;
            }
            curr = curr.next;
        }

        if (xPrev != null && yPrev != null) {
            Node temp = xPrev.next;
            xPrev.next = yPrev.next;
            yPrev.next = temp;
            temp = xPrev.next.next;
            xPrev.next.next = yPrev.next.next;
            yPrev.next.next = temp;
        }
    }

    public void pairwiseSwapElements(Node node) {
        if (node == null || node.next == null) {
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

    public void moveLastElementToFront() {
        if (head == null || head.next == null) {
            return;
        }

        Node curr = head;
        Node prev = null;
        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
        curr.next = head;
        head = curr;
    }

    //20200226
    public MyLinkedList findIntersectionInSortedListIteratively(MyLinkedList other) {
        if (other == null) {
            return new MyLinkedList();
        }
        Node n1 = head, n2 = other.head, n3 = new Node(0), n3Tail = n3;
        while (n1 != null && n2 != null) {
            while (n1.data < n2.data) {
                n1 = n1.next;
            }
            while (n2.data < n1.data) {
                n2 = n2.next;
            }
            if (n1.data == n2.data) {
                n3Tail.next = new Node(n1.data);
                n3Tail = n3Tail.next;
                n1 = n1.next;
                n2 = n2.next;
            }
        }
        MyLinkedList newMll = new MyLinkedList(n3.next);
        return newMll;
    }

    public MyLinkedList findIntersectionInSortedListRecursively(MyLinkedList other) {
        //DID IN ONE SHOT
        Node newNode = new Node(0);
        intersectionUtil(this.head, other.head, newNode);
        MyLinkedList newMll = new MyLinkedList(newNode.next);
        return newMll;
    }

    private void intersectionUtil(Node head1, Node head2, Node newNode) {
        if (head1 == null || head2 == null) {
            return;
        }
        if (head1.data == head2.data) {
            newNode.next = new Node(head1.data);
            intersectionUtil(head1.next, head2.next, newNode.next);
        }
        if (head1.data < head2.data) {
            intersectionUtil(head1.next, head2, newNode);
        }
        if (head2.data < head1.data) {
            intersectionUtil(head1, head2.next, newNode);
        }
    }

    public int findIntersectionPoint(MyLinkedList other) {
        Node currHead = this.head;
        Node otherHead = other.head;
        int size1 = 0, size2 = 0;
        while (currHead != null) {
            size1++;
            currHead = currHead.next;
        }
        while (otherHead != null) {
            size2++;
            otherHead = otherHead.next;
        }
        currHead = this.head;
        otherHead = other.head;
        int diff = size1 - size2;
        if (diff > 0) {
            for (int i = 0; i < diff; i++) {
                currHead = currHead.next;
            }
        } else {
            for (int i = 0; i < diff; i++) {
                otherHead = otherHead.next;
            }
        }
        while (currHead != null && otherHead != null) {
            if (currHead.data == otherHead.data) {
                return currHead.data;
            }
            currHead = currHead.next;
            otherHead = otherHead.next;
        }
        return -1;
    }

    public void segregateEvenThenOdd() {
        //Another approach is to create two new lists and keep adding elements to them: odd and even. Join these lists at the end.
        //https://www.geeksforgeeks.org/segregate-even-and-odd-elements-in-a-linked-list/
        if (head == null) {
            return;
        }
        Node pos = null, prev = null, curr = head;
        //Initialize curr to first odd element
        //Initialize pos to last even element
        while (curr != null && curr.data % 2 == 0) {
            prev = curr;
            pos = curr;
            curr = curr.next;
        }
        //pos = null if first element is odd
        while (curr != null) {
            Node next = curr.next;
            if (curr.data % 2 == 0 && prev != null) {
                prev.next = next;
                //Move curr to last even position and Update pos to that last index
                pos = positionEvenNode(pos, curr);
            } else {
                prev = curr;
            }
            curr = next;
        }
    }

    private Node positionEvenNode(Node pos, Node curr) {
        if (pos == null) {
            curr.next = head;
            head = curr;
        } else {
            //Update pos to last even element
            Node posNext = pos.next;
            pos.next = curr;
            curr.next = posNext;
        }
        pos = curr;
        return pos;
    }

    public void reverseRecursively() {
        //DID IN ONE SHOT
        Node curr = head;
        if (curr == null || curr.next == null) {
            return;
        }
        reverseUtil(curr);
    }

    private void reverseUtil(Node node) {
        tail = node;
        if (node.next == null) {
            head = node;
            return;
        }
        reverseUtil(node.next);
        node.next = null;
        tail.next = node;
        tail = tail.next;
    }

    //20200227
    public void reverseIteratively() {
        if (head == null) {
            return;
        }
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public MyLinkedList mergeTwoSortedListAndReturnReverseList(MyLinkedList other) {
        Node curr = head;
        Node otherCurr = other.head;
        Node newNode = null;
        while (curr != null && otherCurr != null) {
            if (curr.data < otherCurr.data) {
                Node next = curr.next;
                curr.next = newNode;
                newNode = curr;
                curr = next;
            } else {
                Node next = otherCurr.next;
                otherCurr.next = newNode;
                newNode = otherCurr;
                otherCurr = next;
            }
        }

        while (otherCurr != null) {
            Node next = otherCurr.next;
            otherCurr.next = newNode;
            newNode = otherCurr;
            otherCurr = next;
        }
        while (curr != null) {
            Node next = curr.next;
            curr.next = newNode;
            newNode = curr;
            curr = next;
        }
        MyLinkedList mll = new MyLinkedList(newNode);
        return mll;
    }

    //Can be done using two stacks as well: https://www.geeksforgeeks.org/reverse-linked-list-groups-given-size-set-2/
    public MyLinkedList reverseEveryKNodesRecursively(int k) {
        head = reverseEveryKNodesUtil(this.head, k);
        return this;
    }

    private Node reverseEveryKNodesUtil(Node head, int k) {
        Node curr = head;
        Node prev = null;
        Node next = null;
        for (int i = 0; i < k && curr != null; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        if (curr != null) {
            head.next = reverseEveryKNodesUtil(curr, k);
        }
        return prev;
    }

    //20200228
    public void alternateOddEvenNodes() {
        if (head == null || head.next == null) {
            return;
        }
        Node curr = this.head;
        Node nextNode = curr.next;
        while (curr != null && nextNode != null) {
            if ((isEven(curr.data) == isEven(nextNode.data))) {
                alternateOddEvenNodesSwapUtil(curr, nextNode);
            }
            curr = curr.next;
            nextNode = curr != null ? curr.next : null;
        }
    }

    private void alternateOddEvenNodesSwapUtil(Node prev1, Node curr1) {
        Node prev2 = null;
        boolean isEvenCurr1 = isEven(curr1.data);
        Node curr2 = curr1;
        while (curr2 != null && isEven(curr2.data) == isEvenCurr1) {
            prev2 = curr2;
            curr2 = curr2.next;
        }

        if (prev1 != null) prev1.next = curr2;
        if (prev2 != null) prev2.next = curr1;

        //Positioning of this swap code matters
        Node xNext = null, yNext = null;
        if (curr1 != null) {
            xNext = curr1.next;
        }
        if (curr2 != null) {
            yNext = curr2.next;
        }
        if (curr1 != null) {
            curr1.next = yNext;
        }
        if (curr2 != null) {
            curr2.next = xNext;
        }


        /*Node xNext = curr1.next;
        Node yNext = curr2.next;
        curr1.next = yNext;
        curr2.next = xNext;*/
    }

    private boolean isEven(int data) {
        return data % 2 == 0;
    }

    public void alternateDeleteRecursively() {
        Node curr = head;
        alternateDeleteUtil(curr);
    }

    private void alternateDeleteUtil(Node node) {
        if (node == null || node.next == null) {
            return;
        }
        node.next = node.next.next;
        alternateDeleteUtil(node.next);
    }

    public void alternatingSplit() {
        Node curr = head;
        int i = 1;
        Node odd = null;
        Node even = null;
        while (curr != null) {
            Node next = curr.next;
            if (i % 2 == 0) {
                curr.next = odd;
                odd = curr;
            } else {
                curr.next = even;
                even = curr;
            }
            curr = next;
            i++;
        }
        MyLinkedList oddLL = new MyLinkedList(odd);
        MyLinkedList evenLL = new MyLinkedList(even);
        oddLL.printList();
        evenLL.printList();
    }

    public void alternatingSplitWhileMaintainingSequence() {
        Node curr = head;
        int i = 1;
        MyLinkedList oddLL = new MyLinkedList();
        MyLinkedList evenLL = new MyLinkedList();
        while (curr != null) {
            Node next = curr.next;
            if (i % 2 == 0) {
                curr.next = null;
                if (evenLL.tail == null) {
                    evenLL.tail = curr;
                    evenLL.head = curr;
                } else {
                    evenLL.tail.next = curr;
                    evenLL.tail = evenLL.tail.next;
                }
            } else {
                curr.next = null;
                if (oddLL.tail == null) {
                    oddLL.tail = curr;
                    oddLL.head = curr;
                } else {
                    oddLL.tail.next = curr;
                    oddLL.tail = oddLL.tail.next;
                }
            }
            curr = next;
            i++;
        }
        oddLL.printList();
        evenLL.printList();
    }

    public boolean isIdentical(MyLinkedList other) {
        return isIdenticalUtil(this.head, other.head);
    }

    private boolean isIdenticalUtil(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null) {
            return a.data == b.data && isIdenticalUtil(a.next, b.next);
        }

        /*if (a == null && b != null) {
            return false;
        } else if (b == null && a != null) {
            return false;
        }*/
        return false;
    }

    public void deleteNodesWithGreaterValueOnRightSide() {
        if (head == null || head.next == null) {
            return;
        }
        Node curr = head;
        Node prev = null;
        while (curr.next != null && curr.next.data > curr.data) {
            prev = curr;
            curr = curr.next;
        }
        prev = null;
        this.head = curr;
        while (curr.next != null) {
            Node nextNode = curr.next;
            if (curr.next.data > curr.data) {
                prev.next = nextNode;
                curr = nextNode;
                continue;
            }
            prev = curr;
            curr = nextNode;
        }
    }

    public MyLinkedList addTwoNumbersRepresentedByLinkedLists(MyLinkedList other) {
        Node currNode = this.head;
        Node otherNode = other.head;
        int carry = 0;
        Node newNode = null;
        Node tail = null;
        while (currNode != null || otherNode != null) {
            int sum = 0;
            sum += carry;
            if (currNode != null) {
                sum += currNode.data;
            }
            if (otherNode != null) {
                sum += otherNode.data;
            }
            if (newNode == null) {
                newNode = new Node(sum % 10);
                tail = newNode;
            } else {
                tail.next = new Node(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (currNode != null) {
                currNode = currNode.next;
            }
            if (otherNode != null) {
                otherNode = otherNode.next;
            }
        }
        return new MyLinkedList(newNode);
    }

    public void deleteGivenNode(Node head, Node delete) {
        if (head == null) {
            return;
        }
        if (head == delete) {
            this.head = this.head.next;
            return;
        }
        Node curr = head;
        while (curr.next != null && curr.next != delete) {
            curr = curr.next;
        }
        if (curr.next != null) {
            curr.next = curr.next.next;
        }
    }

    //20200229
    public void rotate(int k) {
        if (head == null || head.next == null) return;
        Node curr = head;
        for (int i = 0; i < k - 1; i++) {
            curr = curr.next;
        }
        if (curr.next == null) return;
        Node newHead = curr.next;
        curr.next = null;
        curr = newHead;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = head;
        head = newHead;
    }

    public MyLinkedList sortListWithZeroOneTwo() {
        Node curr = head;

        Node zero = new Node(0);
        Node one = new Node(1);
        Node two = new Node(2);

        Node zeroDup = zero, oneDup = one, twoDup = two;
        while (curr != null) {
            Node next = curr.next;
            if (curr.data == 0) {
                zero.next = curr;
                zero = zero.next;
                zero.next = null;
            }
            if (curr.data == 1) {
                one.next = curr;
                one = one.next;
                one.next = null;
            }
            if (curr.data == 2) {
                two.next = curr;
                two = two.next;
                two.next = null;
            }
            curr = next;
        }
        zero.next = oneDup.next;
        one.next = twoDup.next;
        return new MyLinkedList(zeroDup.next);
    }

    public void deleteNnodesAfterMnodesRecursively(int m, int n) {
        Node curr = head;
        deleteNnodesAfterMnodesUtil(curr, m, n);
    }

    private Node deleteNnodesAfterMnodesUtil(Node node, int m, int n) {
        if (node == null || node.next == null) {
            return node;
        }
        Node curr = node;
        for (int i = 0; i < m - 1; i++) {
            curr = curr.next;
        }
        Node nextNode = curr.next;
        for (int i = 0; i < n && nextNode != null; i++) {
            nextNode = nextNode.next;
        }
        curr.next = deleteNnodesAfterMnodesUtil(nextNode, m, n);
        return node;
    }

    public void pairwiseSwapElements() {
        Node curr = head;
        head = pairwiseSwapElementsUtil(curr, 2);
    }

    private Node pairwiseSwapElementsUtil(Node node, int k) {
        Node prev = null;
        Node curr = node;
        Node next = null;
        for (int i = 0; i < k && curr != null; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        if(curr != null){
            node.next = pairwiseSwapElementsUtil(curr, k);
        }
        return prev;
    }
}