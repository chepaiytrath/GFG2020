package datastructures.binarytree;

import java.util.*;

public class BinaryTree {
    public static class Node {
        public int data;
        public Node left;
        public Node right;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        public Node() {
        }

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public Node root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(int data) {
        this.root = new Node(data);
    }

    private void visit(Node node) {
        System.out.print(node.data + " ");
    }

    public void printPreorder() {
        printPreorder(root);
    }

    private void printPreorder(Node node) {
        if (node == null) {
            return;
        }
        visit(node);
        printPreorder(node.left);
        printPreorder(node.right);
    }

    public void morrisPreorderTraversal() {
        morrisPreorderTraversalUtil(root);
    }

    private void morrisPreorderTraversalUtil(Node node) {
        Node curr = node;
        while (curr != null) {
            if (curr.left == null) {
                visit(curr);
                curr = curr.right;
            } else {
                Node preorderPredecessor = findPreorderPredecessor(curr);
                if (preorderPredecessor.right == null) {
                    preorderPredecessor.right = curr.right;
                    visit(curr);
                    curr = curr.left;
                } else {
                    preorderPredecessor.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    private Node findPreorderPredecessor(Node node) {
        Node curr = node;
        Node left = curr.left;
        while (left.right != null && left.right != curr) {
            left = left.right;
        }
        return left;
    }

    public void printInorder() {
        printInorder(root);
    }

    private void printInorder(Node node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        visit(node);
        printInorder(node.right);
    }

    public void printInorderWithStackWithoutRecursion() {
        //Similar solution at : https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
        Stack<Node> st = new Stack<>();
        Node curr = root;
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

    public void morrisInorderTraversal() {
        morrisInorderTraversalUtil(root);
    }

    private void morrisInorderTraversalUtil(Node node) {
        Node curr = node;
        while (curr != null) {
            if (curr.left == null) {
                visit(curr);
                curr = curr.right;
            } else {
                Node inorderPredecessor = findInorderPredecessor(curr);
                if (inorderPredecessor.right == null) {
                    inorderPredecessor.right = curr;
                    curr = curr.left;
                } else {
                    inorderPredecessor.right = null;
                    visit(curr);
                    curr = curr.right;
                }
            }

        }
    }

    private Node findInorderPredecessor(Node node) {
        Node curr = node;
        Node left = curr.left;
        while (left.right != null && left.right != curr) {
            left = left.right;
        }
        return left;
    }

    public void printPostorder() {
        printPostorder(root);
    }

    private void printPostorder(Node node) {
        if (node == null) {
            return;
        }
        printPostorder(node.left);
        printPostorder(node.right);
        visit(node);
    }

    static class INT {
        int data;
    }

    public void replaceNodeWithSumOfInorderPredecessorAndSuccessor() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        putInorderTraversalNodesIntoList(list, root);
        list.add(0);
        INT i = new INT();
        i.data = 1;
        replaceNodeWithSumOfInorderUtil(list, root, i);
    }

    private void putInorderTraversalNodesIntoList(List<Integer> list, Node node) {
        if (node == null) {
            return;
        }
        putInorderTraversalNodesIntoList(list, node.left);
        list.add(node.data);
        putInorderTraversalNodesIntoList(list, node.right);
    }

    private void replaceNodeWithSumOfInorderUtil(List<Integer> list, Node node, INT i) {
        if (node == null) {
            return;
        }
        replaceNodeWithSumOfInorderUtil(list, node.left, i);
        node.data = list.get(i.data - 1) + list.get(i.data + 1);
        i.data++;
        replaceNodeWithSumOfInorderUtil(list, node.right, i);
    }

    static int count = 0;
    static Node result = new Node();

    public Node populateNthNodeOfInorderTraversal(int k) {
        populateNthNodeOfInorderUtil(root, k, result);
        return result;
    }

    private void populateNthNodeOfInorderUtil(Node node, int k, Node result) {
        if (node == null) {
            return;
        }
        if (count <= k) {
            populateNthNodeOfInorderUtil(node.left, k, result);
            count++;
            if (count == k && result.data == 0) {
                result.data = node.data;
            }
            populateNthNodeOfInorderUtil(node.right, k, result);
        }
    }

    public void levelOrderTraversalUsingQueue() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
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

    public void levelOrderTraversalUsingRecursion() {
        int height = height(root);
        for (int i = 1; i <= height; i++) {
            printGivenLevel(root, i);
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
        int hLeft = height(node.left);
        int hRight = height(node.right);
        if (hLeft > hRight) {
            return hLeft + 1;
        } else {
            return hRight + 1;
        }
    }

    public void levelOrderTraversalInSpiralFormUsingRecursion() {
        int height = height(root);
        boolean dir = false;
        for (int i = 1; i <= height; i++) {
            printGivenLevelAlternateOrder(root, i, dir);
            dir = !dir;
        }
    }

    private void printGivenLevelAlternateOrder(Node node, int level, boolean dir) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.data + " ");
        } else if (level > 1) {
            if (dir != false) {
                printGivenLevelAlternateOrder(node.left, level - 1, dir);
                printGivenLevelAlternateOrder(node.right, level - 1, dir);
            } else {
                printGivenLevelAlternateOrder(node.right, level - 1, dir);
                printGivenLevelAlternateOrder(node.left, level - 1, dir);
            }
        }
    }

    public void levelOrderTraversalInSpiralFormUsingTwoStacks() {
        if (root == null) {
            return;
        }

        Node curr = root;
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
}