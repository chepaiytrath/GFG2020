package algorithm.specialds;

import java.util.Stack;

class QueueUsingTwoStack {
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();

    /////////////////////////////////////////////////////////////////////////////
    // Push O(1) and Pop O(n)

    // Push directly to s1
    void push(int a) {
        s1.push(a);
    }

    // If s2 is empty, first transfer all elements from s1 to s2 and then pop from s2
    // If s2 is not empty, directly pop from s2
    // If both are empty, return -1
    int pop() {
        if (s1.isEmpty() && s2.isEmpty()) {
            return -1;
        }

        if (s2.empty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }


    /////////////////////////////////////////////////////////////////////////////

    // Push O(n) and Pop O(1)

    //Before adding new element, transfer all elements from s1 to s2, then add element to s2. Then transfer back all elements from s2 to s1.
    void push2(int a) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s2.push(a);
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    // Pop directly from s1 if not empty
    int pop2() {
        if (s1.isEmpty()) {
            return -1;
        }
        return s1.pop();
    }

}