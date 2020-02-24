//https://leetcode.com/problems/find-median-from-data-stream/discuss/506772/Java-solution-using-2-heaps.
package company.intuit;

import java.util.PriorityQueue;

class FindMedianInStream {

    /**
     * initialize your data structure here.
     */
    //create 2 heap,one is a Max-heap(smaller) and another is Min-heap(larger).
    //where the numbers less than the middle are put in the smaller one while the numbers greater than the middle are put in the larger one
    //to make it balance,0<=smaller.size()<=larger.size()+1;
    //so in the end,the middle one will be the peek of smaller one or the mean of the smaller.peek() and larger.peak()
    PriorityQueue<Integer> smaller = new PriorityQueue<>((a, b) -> b - a);
    PriorityQueue<Integer> larger = new PriorityQueue<>((a, b) -> a - b);

    public FindMedianInStream() {
    }

    public void addNum(int num) {
        if (smaller.size() == 0 || num <= smaller.peek())
            smaller.add(num);
        else if (larger.size() == 0 || num >= larger.peek())
            larger.add(num);
        else
            //put the num in an arbitrary heap
            smaller.add(num);
        balance();
    }

    private void balance() {
        if (smaller.size() > larger.size() + 1)
            larger.add(smaller.poll());
        else if (smaller.size() < larger.size())
            smaller.add(larger.poll());
    }

    public double findMedian() {
        if (smaller.size() == larger.size() + 1)
            return smaller.peek();
        else
            return (double) (smaller.peek() + larger.peek()) / 2;
    }

    public static void main(String[] args) {
        FindMedianInStream obj = new FindMedianInStream();
        obj.addNum(5);
        obj.addNum(15);
        obj.addNum(1);
        obj.addNum(3);
        obj.addNum(2);
        obj.addNum(8);
        System.out.println(obj.findMedian());
        obj.addNum(7);
        obj.addNum(9);
        obj.addNum(10);
        obj.addNum(6);
        obj.addNum(11);
        obj.addNum(4);
        System.out.println(obj.findMedian());
    }
}