package datastructures.binarytree;

public class BinaryTree {
    public static class Node {
        public int data;
        public Node left;
        public Node right;
        public Node parent;

        @Override
        public String toString() {
            return data + " ";
        }

        public Node() {
        }

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }
    }

    static class QueueNode {
        int dis;
        BinaryTree.Node node;

        QueueNode(int dis, BinaryTree.Node node) {
            this.dis = dis;
            this.node = node;
        }

        @Override
        public String toString() {
            return "QueueNode{" +
                    "node=" + node +
                    '}';
        }
    }

    static class ViewNode {
        int data;
        int level;

        ViewNode(int data, int level) {
            this.data = data;
            this.level = level;
        }
    }

    public Node root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(int data) {
        this.root = new Node(data);
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public int size(){
        return findSize(this.root);
    }

    private int findSize(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + findSize(node.left) + findSize(node.right);
    }
}