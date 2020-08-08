//https://www.geeksforgeeks.org/sort-a-stack-using-recursion/
package algorithm.basictechniques.sorting;

import java.util.ListIterator;
import java.util.Stack;

public class SortStackUsingRecursion {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(30);
        s.push(-5);
        s.push(18);
        s.push(14);
        s.push(-3);
        printStack(s);
        sortStackUsingRecursion(s);
        System.out.println("Stack sorted");
        printStack(s);
    }

    static void printStack(Stack<Integer> s)
    {
        ListIterator<Integer> lt = s.listIterator();

        // forwarding
        while(lt.hasNext())
            lt.next();

        // printing from top to bottom
        while(lt.hasPrevious())
            System.out.print(lt.previous()+" ");
    }

    private static void sortStackUsingRecursion(Stack<Integer> s) {
        if (!s.isEmpty()) {
            Integer x = s.pop();
            sortStackUsingRecursion(s);
            sortedInsertIntoStackAscending(s, x);
        }
    }

    private static void sortedInsertIntoStackDescending(Stack<Integer> s, Integer x) {
        if (s.isEmpty() || x > s.peek()) {
            s.push(x);
            return;
        }
        Integer temp = s.pop();
        sortedInsertIntoStackDescending(s, x);
        s.push(temp);
    }

    private static void sortedInsertIntoStackAscending(Stack<Integer> s, Integer x) {
        if (s.isEmpty() || x < s.peek()) {
            s.push(x);
            return;
        }
        Integer temp = s.pop();
        sortedInsertIntoStackAscending(s, x);
        s.push(temp);
    }
}
