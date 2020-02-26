package datastructure.linkedlist;

public class MyLinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList mll = new MyLinkedList();

        mll.pushToEnd(1);
        mll.pushToEnd(2);
        mll.pushToEnd(3);
        mll.pushToEnd(4);
        mll.pushToEnd(5);
        mll.pushToEnd(6);

        mll.printList();
        mll.pairwiseSwapElements(mll.head);
        System.out.println();
        mll.printList();
    }
}