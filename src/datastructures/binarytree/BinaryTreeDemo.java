package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        CheckingAndPrinting c = new CheckingAndPrinting();

        BinaryTree tree = new BinaryTree(15);
        tree.root.left = new Node(10);
        tree.root.left.left = new Node(8);
        tree.root.left.right = new Node(12);
        tree.root.right = new Node(20);
        tree.root.right.left = new Node(16);
        tree.root.right.right = new Node(25);
        
        c.printLeftmostAndRightmostNodes(tree);
    }
}
