package algorithm.specialds;

import java.util.LinkedList;
import java.util.Queue;

class StackUsingTwoQueues {
    Queue<Integer> q1 = new LinkedList<Integer>();
    Queue<Integer> q2 = new LinkedList<Integer>();


    // Hold new element in q2. Transfer all elements from q1 to q2. Swap queue names.
    void push(int a) {
        q2.add(a);
        while (!q1.isEmpty()) {
            q2.add(q1.poll());
        }
        q1 = q2;
        q2 = new LinkedList<Integer>();
    }

    // Pop directly from q1 if not empty
    int pop() {
        if (!q1.isEmpty()) {
            return q1.poll();
        }
        return -1;
    }
}