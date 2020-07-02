package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        CheckingAndPrinting c = new CheckingAndPrinting();
        // BinaryTree tree = new BinaryTree(1);
        // tree.root.left = new Node(2);
        // tree.root.left.left = new Node(4);
        // tree.root.left.left.left = new Node(9);
        // tree.root.left.left.right = new Node(10);
        // tree.root.left.right = new Node(5);
        // tree.root.right = new Node(3);
        // tree.root.right.left = new Node(8);
        // tree.root.right.left.right = new Node(11);

        BinaryTree tree = new BinaryTree(20);
        tree.root.left = new Node(10);
        tree.root.left.left = new Node(5);
        tree.root.left.right = new Node(15);
        tree.root.left.right.left = new Node(12);
        tree.root.right = new Node(30);
        tree.root.right.left = new Node(25);
        tree.root.right.right = new Node(40);

        c.printAllNodesAtKDistanceFromRootWithoutRecursion(tree, 3);
    }
}
