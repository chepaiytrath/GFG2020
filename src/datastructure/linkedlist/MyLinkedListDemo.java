package datastructure.linkedlist;

import java.util.Arrays;
import java.util.List;

public class MyLinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList mll = new MyLinkedList();
        mll.pushToEnd(0);
        mll.pushToEnd(0);
        mll.pushToEnd(1);


        MyLinkedList mll2 = new MyLinkedList();
        mll2.pushToEnd(1);

        mll.subtractTwoNumbersRepresentedAsLinkedList(mll2);
    }
}