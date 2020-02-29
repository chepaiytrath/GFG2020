package datastructure.linkedlist;

public class MyLinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList mll = new MyLinkedList();
        mll.pushToEnd(1);
        mll.pushToEnd(3);
        mll.pushToEnd(5);
        mll.pushToEnd(7);
        mll.pushToEnd(9);

        MyLinkedList mll2 = new MyLinkedList();
        mll2.pushToEnd(2);
        mll2.pushToEnd(4);
        mll2.pushToEnd(6);
        mll2.pushToEnd(8);
        mll2.pushToEnd(10);

        mll.findIntersectionInSortedListIteratively(mll2).printList();
        //mll.printList();
    }
}