package company.walmart;

import java.util.Stack;

public class SpecialStack extends Stack<Integer> {
    Stack<Integer> min = new Stack<>();

    public void push(int data) {
        if (isEmpty()) {
            super.push(data);
            min.push(data);
        } else {
            super.push(data);
            Integer minTop = min.peek();
            if (data < minTop){
                min.push(data);
            }
        }
    }

    public Integer pop() {
        Integer popped = super.pop();
        if(popped == min.peek()){
            min.pop();
        }
        return popped;
    }

    public Integer getMin() {
        return min.peek();
    }

    public static void main(String[] args) {
        SpecialStack stack = new SpecialStack();
        stack.push(10);
        stack.push(8);
        stack.pop();
        stack.push(15);
        stack.push(19);
        stack.pop();
        stack.push(13);
        stack.push(1);
        stack.push(2);
        stack.push(9);
        System.out.println(stack.getMin());
    }
}
