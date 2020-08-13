package leetcode.allproblems;

import java.util.Stack;

class A0946ValidateStackSequences {
    public static void main(String[] args) {
        int[] pushed = new int[]{};
        int[] popped = new int[]{};
        System.out.println(validateStackSequences(pushed, popped));
    }

    // LOGIC : KEEP A STACK TO STORE PUSHED ELEMENTS IN REVERSE ORDER
    // AFTER EACH ADDITION OF A PUSHED ELEMENT TO STACK, CHECK IF IT IS EQUAL TO POPPED FIRST ELEMENT. KEEP POPPING AND INCREMENT THE POPPED POINTER
    // LASTLY CHECK IF STACK IS EMPTY BECAUSE ALL ELEMENTS MUSTG BE MATCHED BY NOW
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}