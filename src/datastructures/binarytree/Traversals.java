package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

import java.util.*;

public class Traversals {
    private void visit(Node node) {
        System.out.print(node.data + " ");
    }

    public void printPreorder(BinaryTree tree) {
        printPreorder(tree.root);
    }

    private void printPreorder(Node node) {
        if (node == null) {
            return;
        }
        visit(node);
        printPreorder(node.left);
        printPreorder(node.right);
    }

    public void printInorder(BinaryTree tree) {
        printInorder(tree.root);
    }

    private void printInorder(Node node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        visit(node);
        printInorder(node.right);
    }

    public void printPostorder(BinaryTree tree) {
        printPostorder(tree.root);
    }

    private void printPostorder(Node node) {
        if (node == null) {
            return;
        }
        printPostorder(node.left);
        printPostorder(node.right);
        visit(node);
    }

    public void morrisPreorderTraversal(BinaryTree tree) {
        morrisPreorderTraversalUtil(tree.root);
    }

    private void morrisPreorderTraversalUtil(Node node) {
        Node curr = node;
        while (curr != null) {
            if (curr.left == null) {
                visit(curr);
                curr = curr.right;
            } else {
                Node pred = findPredecessor(curr);
                if (pred.right != curr) {
                    visit(curr);
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    public void morrisInorderTraversal(BinaryTree tree) {
        morrisInorderTraversalUtil(tree.root);
    }

    private void morrisInorderTraversalUtil(Node node) {
        Node curr = node;
        while (curr != null) {
            if (curr.left == null) {
                visit(curr);
                curr = curr.right;
            } else {
                Node pred = findPredecessor(curr);
                if (pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    visit(curr);
                    curr = curr.right;
                }
            }

        }
    }

    private Node findPredecessor(Node node) {
        Node curr = node;
        Node left = curr.left;
        while (left.right != null && left.right != curr) {
            left = left.right;
        }
        return left;
    }

    public void printInorderWithStackWithoutRecursion(BinaryTree tree) {
        //Similar solution at : https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
        Stack<Node> st = new Stack<>();
        Node curr = tree.root;
        while (curr != null) {
            st.push(curr);
            curr = curr.left;
        }
        while (!st.isEmpty()) {
            Node node = st.pop();
            visit(node);
            if (node.right != null) {
                curr = node.right;
                while (curr != null) {
                    st.push(curr);
                    curr = curr.left;
                }
            }
        }
    }

    public void printPostOrderWithTwoStacksWithoutRecursion(BinaryTree tree) {
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        Node node = tree.root;
        s1.add(node);

        while (!s1.isEmpty()) {
            Node curr = s1.pop();
            if (null != curr.left) {
                s1.add(curr.left);
            }
            if (null != curr.right) {
                s1.add(curr.right);
            }
            s2.add(curr);
        }

        while (!s2.isEmpty()) {
            System.out.print(s2.pop().data + " ");
        }
    }

    public void printPostOrderWithOneStacksWithoutRecursion(BinaryTree tree) {
        Node curr = tree.root;
        Stack<Node> st = new Stack<>();
        while(!st.isEmpty() || curr != null){
            if(curr != null){
                st.add(curr);
                curr = curr.left;
            }else{
                Node right = st.peek().right;
                if(right == null){
                    Node popped = st.pop();
                    System.out.println(popped.data);
                    while(!st.isEmpty() && popped == st.peek().right){
                        popped = st.pop();
                        System.out.println(popped.data);
                    }
                }else{
                    curr = right;
                }
            }
        }
    }

    public void levelOrderTraversalWithQueue(BinaryTree tree) {
        Queue<Node> q = new LinkedList<>();
        q.add(tree.root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            System.out.print(node.data + " ");
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
    }

    public void levelOrderTraversalWithRecursion(BinaryTree tree) {
        int height = height(tree.root);
        for (int i = 1; i <= height; i++) {
            printGivenLevel(tree.root, i);
            System.out.println();
        }
    }

    public void reverseLevelOrderTraversalWithQueueandStack(BinaryTree tree) {
        Stack<Node> st = new Stack<>();
        Queue<Node> q = new LinkedList<>();
        Node node = tree.root;
        q.add(node);

        while(!q.isEmpty()){
            Node n = q.poll();
            st.add(n);
            if(n.right != null){
                q.add(n.right);
            }
            if(n.left != null){
                q.add(n.left);
            }
        }
        while(!st.isEmpty()){
            System.out.print(st.pop()+" ");
        }
    }

    public void reverseLevelOrderTraversalWithRecursion(BinaryTree tree){
        int height = height(tree.root);
        for (int i = height; i >= 1; i--) {
            printGivenLevel(tree.root, i);
            System.out.println();
        }
    }

    private void printGivenLevel(Node node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.data + " ");
        } else if (level > 1) {
            printGivenLevel(node.left, level - 1);
            printGivenLevel(node.right, level - 1);
        }
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        int hLeft = height(node.left) + 1;
        int hRight = height(node.right) + 1;
        return Math.max(hLeft, hRight);
    }

    public void levelOrderTraversalInSpiralFormWithRecursion(BinaryTree tree) {
        int height = height(tree.root);
        boolean dir = true;
        for (int i = 1; i <= height; i++) {
            dir = !dir;
            printGivenLevelAlternateOrder(tree.root, i, dir);
            System.out.println();
        }
    }

    public void levelOrderTraversalWithDirectionChangeAfterEveryTwoLevels(BinaryTree tree) {
        int height = height(tree.root);
        boolean dir = true;
        int prev = 1;
        for (int i = 1; i <= height; i++) {
            if (i - prev == 2) {
                prev = i;
                dir = !dir;
            }
            printGivenLevelAlternateOrder(tree.root, i, dir);
            System.out.println();
        }
    }

    private void printGivenLevelAlternateOrder(Node node, int level, boolean dir) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.data + " ");
        } else if (level > 1) {
            if (dir) {
                printGivenLevelAlternateOrder(node.left, level - 1, dir);
                printGivenLevelAlternateOrder(node.right, level - 1, dir);
            } else {
                printGivenLevelAlternateOrder(node.right, level - 1, dir);
                printGivenLevelAlternateOrder(node.left, level - 1, dir);
            }
        }
    }

    public void levelOrderTraversalInSpiralFormWithTwoStacks(BinaryTree tree) {
        if (tree.root == null) {
            return;
        }

        Node curr = tree.root;
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        st1.push(curr);

        while (!st1.isEmpty() || !st2.isEmpty()) {
            Node node = null;
            while (!st1.isEmpty()) {
                node = st1.pop();
                System.out.print(node.data + " ");
                if (node.right != null) {
                    st2.push(node.right);
                }
                if (node.left != null) {
                    st2.push(node.left);
                }
            }

            while (!st2.isEmpty()) {
                node = st2.pop();
                System.out.print(node.data + " ");
                if (node.left != null) {
                    st1.push(node.left);
                }
                if (node.right != null) {
                    st1.push(node.right);
                }
            }
        }
    }

    public void diagonalOrderTraversalWithRecursion(BinaryTree tree) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        diagonalOrderTraversalWithRecursionUtil(tree.root, 0, map);
        for (Map.Entry e : map.entrySet()) {
            System.out.println(e.getValue());
        }
    }

    private void diagonalOrderTraversalWithRecursionUtil(Node node, int dis, Map<Integer, List<Integer>> map) {
        if (node == null) {
            return;
        }
        List<Integer> list = map.computeIfAbsent(dis, k-> new ArrayList<>());
        list.add(node.data);
        diagonalOrderTraversalWithRecursionUtil(node.left, dis + 1, map);
        diagonalOrderTraversalWithRecursionUtil(node.right, dis, map);
    }

    public void verticalOrderTraversalWithQueue(BinaryTree tree) {
        Queue<BinaryTree.QueueNode> q = new LinkedList<>();
        q.add(new BinaryTree.QueueNode(0, tree.root));
        Map<Integer, List<Integer>> map = new TreeMap<>();
        while (!q.isEmpty()) {
            BinaryTree.QueueNode popped = q.poll();
            if (popped.node.left != null) {
                q.add(new BinaryTree.QueueNode(popped.dis - 1, popped.node.left));
            }
            if (popped.node.right != null) {
                q.add(new BinaryTree.QueueNode(popped.dis + 1, popped.node.right));
            }
            List<Integer> listVal = map.computeIfAbsent(popped.dis, k -> new ArrayList<>());
            listVal.add(popped.node.data);
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.print(entry.getValue());
        }
    }

    public void verticalOrderTraversalWithRecursion(BinaryTree tree) {
        Node node = tree.root;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        verticalOrderTraversalWithRecursionUtil(node, 0, map);
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            System.out.print(entry.getValue());
        }
    }

    private void verticalOrderTraversalWithRecursionUtil(Node node, int i, Map<Integer, List<Integer>> map) {
        if (null == node) {
            return;
        }
        List<Integer> list = map.computeIfAbsent(i, k -> new ArrayList<>());
        list.add(node.data);
        verticalOrderTraversalWithRecursionUtil(node.left, i - 1, map);
        verticalOrderTraversalWithRecursionUtil(node.right, i + 1, map);
    }
}
