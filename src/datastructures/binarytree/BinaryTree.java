package datastructures.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {
    static class Node {
        int data;
        Node left;
        Node right;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    BinaryTree() {
        root = null;
    }

    BinaryTree(int data) {
        this.root = new Node(data);
    }

    private void visit(Node node) {
        System.out.println(node + " ");
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

    public void morrisInOrderTraversal() {
        morrisInOrderTraversalUtil(root);
    }

    private void morrisInOrderTraversalUtil(Node node) {
        Node curr = node;
        while (curr != null) {
            if (curr.left == null) {
                visit(curr);
                curr = curr.right;
            } else {
                Node predecessor = findPredecessor(curr);
                if (predecessor.right == null) {
                    predecessor.right = curr;
                    curr = curr.left;
                } else {
                    predecessor.right = null;
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

    public void replaceNodeWithSumOfInorderPredecessorAndSuccessor() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        putInorderTraversalNodes(list, root);
        list.add(0);
        INT i = new INT();
        i.data = 1;
        replaceNodeWithSumOfInorderUtil(list, root, i);
    }

    static class INT {
        int data;
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

    private void putInorderTraversalNodes(List<Integer> list, Node node) {
        if (node == null) {
            return;
        }
        putInorderTraversalNodes(list, node.left);
        list.add(node.data);
        putInorderTraversalNodes(list, node.right);
    }


}
