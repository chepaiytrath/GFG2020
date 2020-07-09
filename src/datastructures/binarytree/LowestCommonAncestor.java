package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

import java.util.*;

public class LowestCommonAncestor {
    TraversalAndView t = new TraversalAndView();

    static class BOOLEAN {
        boolean flag = false;
    }

    public void findLowestCommonAncestorWithRootToNodePathComparison(BinaryTree tree, int n1, int n2) {
        List<Integer> list1 = findLowestCommonAncestorWithRootToNodePathComparisonUtil(tree.root, n1);
        List<Integer> list2 = findLowestCommonAncestorWithRootToNodePathComparisonUtil(tree.root, n2);
        System.out.println(list1);
        System.out.println(list2);

        int i = 0;
        if (list1.isEmpty() || list2.isEmpty()) {
            System.out.println("One of the numbers was not found");
            return;
        }
        for (; i < list1.size() && i < list2.size(); i++) {
            if (!(list1.get(i) == list2.get(i))) {
                break;
            }
        }
        System.out.println(list1.get(i - 1));
    }

    private List<Integer> findLowestCommonAncestorWithRootToNodePathComparisonUtil(Node root, int num) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.add(curr);
                if (curr.data == num) {
                    break;
                }
                curr = curr.left;
            }
            if (stack.peek().data == num) {
                List<Integer> list = new ArrayList<Integer>();
                fetchListFromStack(stack, list);
                return list;
            }
            if (stack.peek().right != null) {
                curr = stack.peek().right;
                continue;
            } else {
                curr = stack.pop();
                while (!stack.isEmpty() && curr == stack.peek().right) {
                    curr = stack.pop();
                }
                curr = null;
            }
        }
        return Collections.emptyList();
    }

    private void fetchListFromStack(Stack<Node> stack, List<Integer> list) {
        if (stack.isEmpty()) {
            return;
        }
        Node node = stack.pop();
        fetchListFromStack(stack, list);
        list.add(node.data);
    }

    public void findLowestCommonAncestorWithRecursion(BinaryTree tree, int n1, int n2) {
        BOOLEAN b1 = new BOOLEAN();
        BOOLEAN b2 = new BOOLEAN();
        Node res = findLowestCommonAncestorWithRecursionUtil(tree.root, n1, n2, b1, b2);
        if (b1.flag && b2.flag) {
            System.out.println(res.data);
        }
    }

    private Node findLowestCommonAncestorWithRecursionUtil(Node node, int n1, int n2, BOOLEAN b1, BOOLEAN b2) {
        if (node == null) {
            return null;
        }
        Node temp = null;
        if (node.data == n1) {
            b1.flag = true;
        }
        if (node.data == n1) {
            b1.flag = true;
            temp = node;
        }
        if (node.data == n2) {
            b2.flag = true;
            temp = node;
        }
        Node left = findLowestCommonAncestorWithRecursionUtil(node.left, n1, n2, b1, b2);
        Node right = findLowestCommonAncestorWithRecursionUtil(node.right, n1, n2, b1, b2);
        if (temp != null) {
            return temp;
        }
        if (left != null && right != null) {
            return node;
        }
        return left != null ? left : right;
    }

    public void findLowestCommonAncestorWithParentPointer(BinaryTree tree, Node node1, Node node2) {
        //SAMPLE INPUT
        /*Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node3 = new Node(3);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        BinaryTree tree = new BinaryTree(1);

        node2.parent = node1;
        node3.parent = node1;
        node4.parent = node2;
        node5.parent = node2;
        node6.parent = node3;
        node7.parent = node3;

        tree.root.left = node2;
        tree.root.left.left = node4;
        tree.root.left.right = node5;
        tree.root.right = node3;
        tree.root.right.left = node6;
        tree.root.right.right = node7;

        lca.findLowestCommonAncestorWithParentPointer(tree, node5, node4);*/

        Node res = findLowestCommonAncestorWithParentPointerUtil(node1, node2);
        if (res != null) {
            System.out.println(res.data);
        }
    }

    private Node findLowestCommonAncestorWithParentPointerUtil(Node node1, Node node2) {
        Set<Node> set = new HashSet<>();
        while (node1 != null) {
            set.add(node1);
            node1 = node1.parent;
        }

        while (node2 != null && !set.isEmpty()) {
            if (set.contains(node2)) {
                return node2;
            }
            node2 = node2.parent;
        }
        return null;
    }

    public void printAncestorsOfGivenNodeWithRecursionAndExtraArray(BinaryTree tree, int num) {
        printAncestorsOfGivenNodeWithRecursionAndExtraArrayUtil(tree.root, 0, num, new int[1000]);
    }

    private boolean printAncestorsOfGivenNodeWithRecursionAndExtraArrayUtil(Node node, int level, int num, int[] arr) {
        if (node == null) {
            return false;
        }
        if (node.data == num) {
            for (int i = 0; i < level; i++) {
                System.out.print(arr[i] + " ");
            }
            return true;
        }
        arr[level] = node.data;
        return printAncestorsOfGivenNodeWithRecursionAndExtraArrayUtil(node.left, level + 1, num, arr) || printAncestorsOfGivenNodeWithRecursionAndExtraArrayUtil(node.right, level + 1, num, arr);
    }

    public void printAncestorsOfGivenNodeWithRecursionWithoutExtraArray(BinaryTree tree, int num) {
        Stack<Integer> st = new Stack<>();
        printAncestorsOfGivenNodeWithRecursionWithoutExtraArrayUtil(tree.root, num, st);
        while(!st.isEmpty()){
            System.out.print(st.pop() + " ");
        }
    }

    private boolean printAncestorsOfGivenNodeWithRecursionWithoutExtraArrayUtil(Node node, int num, Stack<Integer> st) {
        if (node == null) {
            return false;
        }
        if (node.data == num) {
            return true;
        }
        if (printAncestorsOfGivenNodeWithRecursionWithoutExtraArrayUtil(node.left, num, st) || printAncestorsOfGivenNodeWithRecursionWithoutExtraArrayUtil(node.right, num, st)) {
            //System.out.print(node.data + " ");
            st.add(node.data);
            return true;
        }
        return false;
    }

    public void printAncestorsOfGivenNodeWithoutRecursionWithStack(BinaryTree tree, int num) {
        Stack<Node> st = new Stack<>();
        Node curr = tree.root;
        boolean flag = false;
        while (!st.isEmpty() || curr != null) {
            while (curr != null) {
                if (curr.data == num) {
                    flag = true;
                    break;
                }
                st.add(curr);
                curr = curr.left;
            }
            if (flag) {
                break;
            }
            curr = st.peek();
            if (curr.right != null) {
                curr = curr.right;
                continue;
            } else {
                curr = st.pop();
                while (!st.isEmpty() && curr == st.peek().right) {
                    curr = st.pop();
                }
                curr = null;
            }
        }
        System.out.println(st);
    }


    public void printKthAncestorOfANodeInBinaryTreeWithRecursionAndExtraArray(BinaryTree tree, int val, int k) {
        printKthAncestorOfANodeInBinaryTreeWithRecursionAndExtraArrayUtil(tree.root, val, k, 0, new int[1000]);
    }

    private boolean printKthAncestorOfANodeInBinaryTreeWithRecursionAndExtraArrayUtil(Node node, int val, int k, int level, int[] arr) {
        if (node == null) {
            return false;
        }
        if (node.data == val) {
            if (level - k < 0) {
                System.out.print(-1);
            } else {
                System.out.print(arr[level - k]);
            }
            return true;
        }
        arr[level] = node.data;
        return printKthAncestorOfANodeInBinaryTreeWithRecursionAndExtraArrayUtil(node.left, val, k, level + 1, arr) || printKthAncestorOfANodeInBinaryTreeWithRecursionAndExtraArrayUtil(node.right, val, k, level + 1, arr);
    }

    static class INTEGER {
        int val = 0;
    }

    public void printKthAncestorOfANodeInBinaryTreeWithRecursionWithoutExtraArray(BinaryTree tree, int val, int k) {
        INTEGER count = new INTEGER();
        boolean res = printKthAncestorOfANodeInBinaryTreeWithRecursionWithoutExtraArrayUtil(tree.root, val, k, count);
        if (count.val > 0) {
            System.out.print(-1);
        }
    }

    private boolean printKthAncestorOfANodeInBinaryTreeWithRecursionWithoutExtraArrayUtil(Node node, int val, int k, INTEGER count) {
        if (node == null) {
            return false;
        }
        if (node.data == val) {
            count.val = k;
            return true;
        }
        if (printKthAncestorOfANodeInBinaryTreeWithRecursionWithoutExtraArrayUtil(node.left, val, k, count) || printKthAncestorOfANodeInBinaryTreeWithRecursionWithoutExtraArrayUtil(node.right, val, k, count)) {
            count.val--;
            if (count.val == 0) {
                System.out.print(node.data);
            }
            return true;
        }
        return false;
    }

    public void printKthAncestorOfANodeInBinaryTreeWithoutRecursionWithStack(BinaryTree tree, int val, int k) {
        Stack<Node> st = new Stack<>();
        Node curr = tree.root;
        boolean flag = false;
        while (!st.isEmpty() || curr != null) {
            while (curr != null) {
                if(curr.data == val){
                    flag = true;
                    break;
                }
                st.add(curr);
                curr = curr.left;
            }
            if(flag){
                break;
            }
            curr = st.peek();
            if(curr.right != null){
                curr = curr.right;
                continue;
            }else{
                curr = st.pop();
                while(!st.isEmpty() && curr == st.peek().right){
                    curr = st.pop();
                }
                curr = null;
            }
        }
        findKthElementInStack(st, k);
    }

    private void findKthElementInStack(Stack<Node> st, int i) {
        while(i >= 1 && !st.isEmpty()){
            Node temp = st.pop();
            if(i == 1){
                System.out.print(temp.data);
            }
            i--;
        }
        if(i > 0){
            System.out.print(-1);
        }
    }

    public void printCommonPathToTheTwoPathsFromRootToTwoGivenNodes(BinaryTree tree, int n1, int n2){

    }
}