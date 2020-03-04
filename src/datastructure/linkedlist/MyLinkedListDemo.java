package datastructure.linkedlist;

public class MyLinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList mll = new MyLinkedList();
        mll.pushToEnd(23);
        mll.pushToEnd(28);
        mll.pushToEnd(28);
        mll.pushToEnd(35);
        mll.pushToEnd(49);
        mll.pushToEnd(49);
        mll.pushToEnd(53);
        mll.pushToEnd(53);


        mll.removeAllOccurrencesOfDuplicatesFromSortedLL();
        mll.printList();
    }
}