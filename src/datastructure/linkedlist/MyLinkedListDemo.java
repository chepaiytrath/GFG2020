package datastructure.linkedlist;

public class MyLinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList mll = new MyLinkedList();
        mll.pushToEnd(1);
        mll.pushToEnd(1);
        mll.pushToEnd(0);
        mll.pushToEnd(0);
        mll.pushToEnd(0);
        mll.pushToEnd(1);

        MyLinkedList mll2 = new MyLinkedList();
        mll2.pushToEnd(3);
        mll2.pushToEnd(1);
        mll2.pushToEnd(2);

        mll.subtractTwoNumbersRepresentedAsLinkedList(mll2);
        //mll.printList();
    }
}