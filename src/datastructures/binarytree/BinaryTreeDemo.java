package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        CheckingAndPrinting c = new CheckingAndPrinting();

        BinaryTree tree = new BinaryTree(3);
        tree.root.left = new Node(2);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(4);
        tree.root.right = new Node(5);
        c.printLevelOfAllNodesInBinaryTreeWithoutRecursionWithQueue(tree);
    }
}
