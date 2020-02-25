package datastructure.linkedlist;

public class MyLinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList mll = new MyLinkedList();

        mll.pushToEnd(11);
        mll.pushToEnd(11);
        mll.pushToEnd(11);
        mll.pushToEnd(21);
        mll.pushToEnd(43);
        mll.pushToEnd(43);
        mll.pushToEnd(60);

        System.out.println("Initial data in LinkedList");
        mll.printList();
        mll.removeDuplicatesFromSortedLinkedList();
        System.out.println("Data after removing duplicates");
        mll.printList();
    }
}