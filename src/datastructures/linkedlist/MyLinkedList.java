package datastructures.linkedlist;

import java.util.List;
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

    public boolean checkIfPalindromeIteratively() {
        //Push all elements to stack. Then pop one by one and compare with each element of the list.
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
        //In each stack, left = head. Move left to its next only if left.data == right.data
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

    public void removeDuplicatesFromSortedLLIteratively() {
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

    // SWAPPING DATA
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

    // CHANGE NEXT POINTER
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
        if (curr != null) {
            node.next = pairwiseSwapElementsUtil(curr, k);
        }
        return prev;
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
    public MyLinkedList findIntersectionInSortedLLIteratively(MyLinkedList other) {
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

    public MyLinkedList findIntersectionInSortedLLRecursively(MyLinkedList other) {
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
                pos = placeEvenNode(pos, curr);
            } else {
                prev = curr;
            }
            curr = next;
        }
    }

    private Node placeEvenNode(Node pos, Node curr) {
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
        // Recursively move till last node. Mark it head and tail both.
        // In each rollback, point tail.next to node and tail = tail.next,
        // so that last rollback makes first node as tail
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

    // DIFFERENT APPROACH
    static Node res = null;

    public Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        reverse(null, head);
        return res;
    }

    private static void reverse(Node prev, Node curr) {
        if (curr.next == null) {
            res = curr;
            res.next = prev;
            return;
        }
        reverse(curr, curr.next);
        curr.next = prev;
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

    private Node reverseEveryKNodesUtil(Node node, int k) {
        Node curr = node;
        Node prev = null;
        Node next = null;
        for (int i = 0; i < k && curr != null; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        if (curr != null) {
            node.next = reverseEveryKNodesUtil(curr, k);
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

    // REVERSE LIST
    // SKIP LESSER ELEMENTS
    // REVERSE LIST AGAIN

    // DONT FOLLOW INTUITIVE APPROACH
    // OF REMOVING THE INITIAL ONES AND THEN ONLY THE ONES WHICH ARE LESSER THAN NEXT
    // THAT WONT WORK FOR 12->15->10->16->5->6->2->3 WHERE ANSWER SHOULD BE 16 6 3
    // THAT GIVES 15 16 6 3
    public void deleteNodesWithGreaterValueOnRightSide() {
        head = reverse(head);

        Node curr = head;
        Node max = curr;
        // DONT GO BEYOND LAST NODE
        while (curr != null && curr.next != null) {
            // FIRST NODE IN REVERSE ORDER WILL NEVER HAVE TO BE CHECKED
            // BECAUSE IT CANT HAVE ANY LESSER NODE TO ITS LEFT
            if(curr.next.data < max.data){
                curr.next = curr.next.next;
            }else{
                curr = curr.next;
                max = curr;
            }
        }

        head = reverse(head);
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

        one.next = twoDup.next; // Keep this before next for edge case where 1's are missing
        zero.next = oneDup.next;
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


    //#AMAZON
    //https://www.geeksforgeeks.org/rearrange-a-given-linked-list-in-place/
    public void rearrangeInPlaceRecursively() {
        left = head;
        rearrangeInPlaceRecursivelyUtil(null, head);
    }

    private void rearrangeInPlaceRecursivelyUtil(Node prev, Node curr) {
        if (curr == null) {
            return;
        }
        rearrangeInPlaceRecursivelyUtil(curr, curr.next);

        // left.next == null IN CASE OF ODD NUMBER OF ELEMENTS
        // left.next != null && left.next.next == null IN CASE OF EVEN NUMBER OF ELEMENTS
        // PERFORM REORDERING IF BOTH THE CASES ARE FALSE i.e. THE LEFT HAS NOT YET REACHED ITS FINAL POSITION
        // AND CAN STILL ACCOMMODATE REORERING
        if (left != null && !(left.next == null || left.next.next == null)) {
            Node next = left.next;
            left.next = curr;
            curr.next = next;
            prev.next = null;
            left = left.next.next;
        }
    }

    public void rearrangeInPlaceByReversingSecondHalfAndMerging() {
        Node curr = head;
        Node secondHalf = findMiddle(curr);
        secondHalf = reverse(secondHalf);

        //In case of odd number of elements, the first half contains the extra element
        Node firstHalf = head;
        while (firstHalf != null) {
            Node firstNext = firstHalf.next;
            Node secondNext = null;
            if (secondHalf != null) {
                secondNext = secondHalf.next;
                secondHalf.next = firstNext;
            }

            firstHalf.next = secondHalf;
            firstHalf = firstNext;
            secondHalf = secondNext;
        }
    }

    private Node findMiddle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node secondHalf = slow.next;
        slow.next = null;
        return secondHalf;
    }

    private Node reverse(Node head) {
        Node current = head;
        Node prev = null;

        while (current != null) {
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    // BETTER SOLUTION BELOW THIS
    public MyLinkedList sortListWhichIsSortedAlternatingAscendingAndDescending() {
        if (head == null) {
            return null;
        }
        Node curr = head;
        Node ascEnd = new Node(0);
        Node descEnd = new Node(0);

        Node asc = ascEnd, desc = descEnd;
        int i = 0;
        while (curr != null) {
            Node nextNode = curr.next;
            if (i % 2 == 0) {
                ascEnd.next = curr;
                ascEnd = ascEnd.next;
                ascEnd.next = null;
            } else {
                descEnd.next = curr;
                descEnd = descEnd.next;
                descEnd.next = null;
            }
            i++;
            curr = nextNode;
        }
        desc = reverse(desc.next);

        asc = asc.next;
        Node finalNode = new Node(0);
        curr = finalNode;

        //Merge asc and reverse of desc lists
        while (asc != null && desc != null) {
            if (asc.data < desc.data) {
                Node ascNext = asc.next;
                finalNode.next = asc;
                finalNode = finalNode.next;
                finalNode.next = null;
                asc = ascNext;
            } else {
                Node descNext = desc.next;
                finalNode.next = desc;
                finalNode = finalNode.next;
                finalNode.next = null;
                desc = descNext;
            }
        }
        if (asc != null) {
            finalNode.next = asc;
        } else {
            finalNode.next = desc;
        }
        return new MyLinkedList(curr.next);
    }

    // BETTER THAN ABOVE SOLUTION
    public static Node sortListWhichIsSortedAlternatingAscendingAndDescending2(Node head) {
        Node curr = head;
        Node half = null;

        while (curr != null && curr.next != null) {
            Node next = curr.next;
            Node nextnext = curr.next.next;

            next.next = half;
            half = next;

            curr.next = nextnext;
            curr = nextnext;
        }

        head = merge(head, half);
        return head;
    }

    private static Node merge(Node first, Node second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }

        if (first.data < second.data) {
            first.next = merge(first.next, second);
            return first;
        } else {
            second.next = merge(first, second.next);
            return second;
        }
    }


    public void sortLinkedListWhichIsAlreadySortedByAbsoluteValues() {
        Node curr = head;
        if (curr == null || curr.next == null) {
            return;
        }
        while (curr.data < 0) {
            if (curr.next.data < 0) {
                Node nextNode = curr.next;
                curr.next = nextNode.next;
                nextNode.next = head;
                head = nextNode;
            } else {
                break;
            }
        }
        Node prev = curr;
        curr = curr.next;
        while (curr != null) {
            if (curr.data >= 0) {
                prev = curr;
                curr = curr.next;
            } else {
                Node nextNode = curr.next;
                prev.next = nextNode;
                curr.next = head;
                head = curr;
                curr = nextNode;
            }
        }
    }


    public void sortLinkedListWhichIsAlreadySortedByAbsoluteValuesEfficiently() {
        if (head == null) {
            return;
        }

        Node prev = head;
        Node curr = prev.next;
        while (curr != null) {
            Node nextNode = curr.next;
            if (curr.data < prev.data) {
                prev.next = nextNode;
                curr.next = head;
                head = curr;
            } else {
                prev = curr;
            }
            curr = nextNode;
        }
    }

    public void deleteLastOccurenceOfAnItem(int n) {
        //Better solution at https://www.geeksforgeeks.org/delete-last-occurrence-of-an-item-from-linked-list/
        Node curr = head;
        Node prev = null;
        Node pos = null;
        while (curr != null) {
            if (curr.data == n) {
                pos = prev;
            }
            prev = curr;
            curr = curr.next;
        }
        if (pos != null && pos.next != null) {
            pos.next = pos.next.next;
        } else if (pos == null && head.data == n) {
            head = head.next;
        } else {
            return;
        }
    }

    static int length = 0;

    public int decimalEquivalentOfBinaryLinkedList() {
        Node curr = head;
        return decimalEquivalentOfBinaryLinkedListUtil(curr, 1);
    }

    private int decimalEquivalentOfBinaryLinkedListUtil(Node node, int index) {
        if (node == null) {
            length = index - 1;
            return 0;
        }
        int restSum = decimalEquivalentOfBinaryLinkedListUtil(node.next, index + 1);
        int currSum = node.data * (int) Math.pow(2, Math.abs(index - length));
        return restSum + currSum;
    }

    public void subtractTwoNumbersRepresentedAsLinkedList(MyLinkedList other) {
        //Recursive solution at : https://www.geeksforgeeks.org/subtract-two-numbers-represented-as-linked-lists/
        if (other == null) {
            return;
        }
        Node curr1 = head;
        Node curr2 = other.head;
        if (curr1 == null || curr2 == null) {
            return;
        }
        boolean borrowed = false;
        Node result = null, finalNode = null;
        while (curr1 != null && curr2 != null) {
            if (borrowed && curr1.data > 0) {
                curr1.data = curr1.data - 1;
                borrowed = false;
            }
            if (borrowed && curr1.data == 0) {
                curr1.data = 9;
            }
            if (!borrowed && curr1.data < curr2.data) {
                curr1.data = curr1.data + 10;
                borrowed = true;
            }

            int diff = curr1.data;
            diff -= curr2.data;
            if (result == null) {
                result = new Node(diff);
                finalNode = result;
            } else {
                finalNode.next = new Node(diff);
                finalNode = finalNode.next;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        if (curr2 == null && curr1 != null) {
            while (curr1 != null) {
                if (borrowed && curr1.data > 0) {
                    curr1.data = curr1.data - 1;
                    borrowed = false;
                }
                if (borrowed && curr1.data == 0) {
                    curr1.data = 9;
                }

                int diff = curr1.data;
                if (result == null) {
                    result = new Node(diff);
                    finalNode = result;
                } else {
                    finalNode.next = new Node(diff);
                    finalNode = finalNode.next;
                }
                curr1 = curr1.next;
            }
        }

        MyLinkedList response = new MyLinkedList(result);
        response.reverseRecursively();
        response.printList();
    }

    //20200303

    public void partitionListAroundGivenValue(int x) {
        if (head == null) {
            return;
        }
        Node curr = head;
        Node prev = null;
        Node lessLast = null;
        while (curr != null && curr.data > x) {
            prev = curr;
            curr = curr.next;
        }
        while (curr != null) {
            if (lessLast == null && (curr.data == x || curr.data < x)) {
                Node currNext = curr.next;
                lessLast = curr;
                if (prev != null) {
                    prev.next = currNext;
                }
                curr = currNext;
                if (lessLast != head) lessLast.next = head;
                head = lessLast;
                continue;
            } else if (lessLast != null && (curr.data == x || curr.data < x)) {
                Node lessLastNext = lessLast.next;
                Node currNext = curr.next;
                if (prev != null) {
                    prev.next = currNext;
                }
                lessLast.next = curr;
                if (curr.data == x) {
                    lessLast.next.next = lessLastNext;
                } else {
                    lessLast = lessLast.next;
                    lessLast.next = lessLastNext;
                }
                curr = currNext;
                continue;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    //20200304
    public boolean checkIfLinkedListWithLoopIsPalindrome() {
        //https://www.geeksforgeeks.org/check-linked-list-loop-palindrome-not/
        if (head == null) {
            return false;
        } else if (head.next == null) {
            return true;
        }
        detectAndRemoveLoop();
        return checkIfPalindromeRecursively();
    }

    public void detectAndRemoveLoop() {
        Node slow = head;
        Node fast = head;

        while (slow != null && fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                removeLoop(head, fast);
                break;
            }
        }
    }

    private void removeLoop(Node head, Node loopNode) {
        Node headNode = head;
        while (true) {
            Node curr = loopNode;
            while (curr.next != loopNode && curr.next != headNode) {
                curr = curr.next;
            }
            if (curr.next == headNode) {
                curr.next = null;
                return;
            }
            headNode = headNode.next;
        }
    }

    public int lengthOfLongestPalindromeInList() {
        //https://www.geeksforgeeks.org/length-longest-palindrome-list-linked-list-using-o1-extra-space/
        return 0;
    }

    public void moveAllOccurrencesOfKeyToEnd(int key) {
        if (head == null) {
            return;
        }
        Node x = head;
        Node curr = head;
        Node node = null;
        while (curr != null && curr.data == key) {
            Node currNext = curr.next;
            curr.next = node;
            node = curr;
            curr = currNext;
        }
        head = curr;

        Node prev = null;
        while (curr != null) {
            if (curr.data != key) {
                prev = curr;
                curr = curr.next;
            } else {
                Node currNext = curr.next;
                curr.next = node;
                node = curr;
                prev.next = currNext;
                curr = currNext;
            }
        }
        prev.next = node;
    }

    public void removeAllOccurrencesOfDuplicatesFromSortedLL() {
        Node dummy = new Node(0);
        dummy.next = head;
        Node curr = head;
        Node prev = dummy;

        while (curr != null && curr.next != null) {
            if (curr.next.data != curr.data) {
                prev = curr;
                curr = curr.next;
            } else if (curr.next.data == curr.data) {
                curr = removeAllOccurrencesNodesUtil(curr);
                if (prev != null) {
                    prev.next = curr;
                }
            }
        }
        head = dummy.next;
    }

    private Node removeAllOccurrencesNodesUtil(Node curr) {
        int data = curr.data;
        while (curr != null && curr.data == data) {
            curr = curr.next;
        }
        return curr;
    }

    public void removeEveryKthNode(int k) {
        if (k == 1 || head == null) {
            head = null;
            return;
        }
        Node curr = head;
        Node prev = null;
        int count = 0;
        while (curr != null) {
            if (++count == k) {
                prev.next = curr.next;
                count = 0;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
    }

    public MyLinkedList mergeTwoSortedLinkedListIteratively(MyLinkedList other) {
        Node first = head;
        Node second = other.head;
        Node dummy = new Node(0);
        Node currDummy = dummy;
        while (first != null && second != null) {
            if (first.data < second.data) {
                Node firstNext = first.next;
                currDummy.next = first;
                first = firstNext;
            } else {
                Node secondNext = second.next;
                currDummy.next = second;
                second = secondNext;
            }
            currDummy = currDummy.next;
        }
        if (first == null) {
            currDummy.next = second;
        }
        if (second == null) {
            currDummy.next = first;
        }
        MyLinkedList result = new MyLinkedList(dummy.next);
        return result;
    }

    public MyLinkedList mergeTwoSortedLinkedListRecursively(MyLinkedList other) {
        MyLinkedList result = new MyLinkedList(mergeTwoSortedLinkedListUtil(this.head, other.head));
        return result;
    }

    private Node mergeTwoSortedLinkedListUtil(Node first, Node second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        if (first.data < second.data) {
            first.next = mergeTwoSortedLinkedListUtil(first.next, second);
            return first;
        } else {
            second.next = mergeTwoSortedLinkedListUtil(first, second.next);
            return second;
        }
    }

    public MyLinkedList mergeKSortedLinkedLists(List<MyLinkedList> lol) {
        int k = lol.size();
        MyLinkedList response = null;
        for (int i = 0; i < k; i++) {
            if (response == null) {
                response = lol.get(i);
            } else {
                response = response.mergeTwoSortedLinkedListRecursively(lol.get(i));
            }
        }
        return response;
    }
}